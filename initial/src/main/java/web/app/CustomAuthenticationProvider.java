package web.app;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {


//    @Override
//    public Authentication authenticate(Authentication auth) throws
//            AuthenticationException {
//        String userName = auth.getName();
//        String password = auth.getCredentials().toString();
//        if ("user".equals(userName) && "pass".equals(password)){
//            Authentication authentication = new
//                    UsernamePasswordAuthenticationToken(userName, "",
//                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
//                            new SimpleGrantedAuthority("ROLE_ADMIN")));
//            return authentication;
//        }
//        throw new BadCredentialsException("User login/pass not correct");
//    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
//        return true;
    }
}
