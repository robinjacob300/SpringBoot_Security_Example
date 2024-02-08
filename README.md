#Important Instructions


#### H2 database console is not accessble in this project spring securiy has to be disable
Due to security policies of spring we will have to enable the h2-console property to see the 
tables in the console after logging in to the console.
in our security config we have made some changes so that the console can be seen
easily.

### 3 configurations are presented in this project for spring security  
configuratuions are enabled or disabled through property files  
conditional annotation is used to enable or disable the configuration.

csrf need to be disabled otherwise PUT, PATCH,DELETE will not work.
don't know why may be due to unstable spring security.

these are the configurations

disableExternalUserConfigs=false
SpringbootSecurityBasoConfiguration=false
thirdsecuriyConfig=false

##### com.example.securityBase.SecConfiguration.thirdSecurityConfiguration

this is the third configuration.  
this is the configuration currently used when the application runs.  


if no configuration is used spring default configuration will be used.  

Currently the application is using the thirdSecurityConfiguration

##	@PreAuthorize("hasRole('LOTUS')")

** when we use preAuthore ** it has a ** prefix call ROLE_ ** which is  
provided by the spring security as a default behaviour. it can be customized  
as per the developers need.  
when working with ** hasRole ** create a user with ROLE_anything  
if the user is custom one inherited from ** userdetails ** and ** userdetailsService **  

For example this json request body can be used with our /stsd enpoint in our controller.
which will create a user
{
    "userid":"asdf",
    "username":"na4",
    "password":"1234123",
    "secretQuestion" : "good",
    "role" : "ROLE_LOTUS"
}

so this is only applicable for hasRole.

Also the PreAuthorize anotation allows complicated spell langs.  
The following examples are provided by ChatGPT not tested and for the reference only.  

```java

@PreAuthorize("(hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')) and hasPermission('resource', 'write')")
public void adminOrManagerWriteResourceMethod() {
    // Method accessible to users with either "ROLE_ADMIN" or "ROLE_MANAGER" authority
    // and having the 'write' permission on the 'resource'
}

@PreAuthorize("#userId == authentication.principal.id")
public void methodWithParameterSecurity(@PathVariable Long userId) {
    // Method accessible if the 'userId' path variable matches the authenticated user's id
}

@PreAuthorize("#userId == authentication.principal.id")
public void methodWithParameterSecurity(@PathVariable Long userId) {
    // Method accessible if the 'userId' path variable matches the authenticated user's id
}

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
public void adminOrManagerMethod() {
    // Method accessible to users with either "ROLE_ADMIN" or "ROLE_MANAGER" authority
}

@PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
public void adminAndUserMethod() {
    // Method accessible to users with both "ROLE_ADMIN" and "ROLE_USER" authorities
}

@PreAuthorize("hasPermission('resource', 'read')")
public void readResourceMethod() {
    // Method accessible if the user has the 'read' permission on the 'resource'
}

```

Reference Links:

https://howtodoinjava.com/spring-security/spring-boot-role-based-authorization/

https://howtodoinjava.com/spring-security/spring-boot-role-based-authorization/


END OF README
