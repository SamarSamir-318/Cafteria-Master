package eg.gov.iti.cafetria.security;
//
//import com.jediver.springsecurity.hellodbwebspringsecurity.model.Role;

import eg.gov.iti.cafetria.model.dal.domain.Privilege;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.service.PrivilageService;
import eg.gov.iti.cafetria.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private Client client;
    private WebTarget target;
    private String serviceURL;
    @Autowired
    private PrivilageService privilegeService;

//?username=ibrahiem@yahoo.com
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        client = ClientBuilder.newClient();
        target = client.target(serviceURL)
                .queryParam("username", username);
        String responseMessage = target.request().get(String.class);
        if (responseMessage.equalsIgnoreCase("fail")) {
            return null;
        } else {
            List<Privilege> privileges = privilegeService.findAll(username);
            List<GrantedAuthority> authorities = buildUserAuthority(privileges);
            return buildUserForAuthentication(username, responseMessage, authorities);
        }
    }

    private User buildUserForAuthentication(String username, String password, List<GrantedAuthority> authorities) {
        return new User(username, password, true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<Privilege> userPrivileges) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        // Build user's authorities
        for (Privilege userPrivilege : userPrivileges) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userPrivilege.getName()));
        }
        return grantedAuthorities;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

}
