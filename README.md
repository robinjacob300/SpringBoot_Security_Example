#Important Instructions


#### H2 database console is not accessble in this project spring securiy has to be disable


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
