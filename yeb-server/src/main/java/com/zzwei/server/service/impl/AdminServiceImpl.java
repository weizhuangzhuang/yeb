package com.zzwei.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzwei.server.config.security.JwtTokenUtil;
import com.zzwei.server.mapper.AdminMapper;
import com.zzwei.server.mapper.AdminRoleMapper;
import com.zzwei.server.mapper.RoleMapper;
import com.zzwei.server.pojo.Admin;
import com.zzwei.server.pojo.AdminRole;
import com.zzwei.server.pojo.Role;
import com.zzwei.server.service.IAdminService;
import com.zzwei.server.utils.AdminUtils;
import com.zzwei.server.utils.RSAUtils;
import com.zzwei.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        System.out.println(username + "====" + RSAUtils.decryptBase64(username));
        System.out.println(password + "====" + RSAUtils.decryptBase64(password));
        System.out.println(code + "====" + RSAUtils.decryptBase64(code));
        //RSAUtils.decryptBase64(username); //对加密的用户名解密
        //RSAUtils.decryptBase64(password);//对加密的密码解密
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(captcha) || !captcha.equalsIgnoreCase(RSAUtils.decryptBase64(code))) {
            return RespBean.error("验证码输入错误，请重新输入");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(RSAUtils.decryptBase64(username));
        if (null == userDetails || !passwordEncoder.matches(RSAUtils.decryptBase64(password), userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用,请联系管理员");
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
        //.eq("enabled", true));
    }

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 获取所有人员信息
     *
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmin(String keywords) {
        return adminMapper.getAllAdmin(AdminUtils.getAdminInfo().getId(), keywords);
    }

    /**
     * 根据用户id更新角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    @Transactional
    @Override
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        //更新之前先清空角色
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        if (null == rids || 0 == rids.length) {
            return RespBean.success("角色更新成功");
        }
        Integer result = adminRoleMapper.updateAdminRole(adminId, rids);
        if (result == rids.length) {
            return RespBean.success("角色更新成功");
        } else {
            return RespBean.error("角色更新失败");
        }
    }
}
