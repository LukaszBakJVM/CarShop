# CarShop  in progress

https://carshop.freeddns.org       -->aplikacja uruchomiona w Docker Container, proxy nginx
# git clone  https://github.com/LukaszBakJVM/CarShop
# maven clean
# maven install 
# 
#########################################
# FROM   openjdk:21-slim-bookworm        #
# EXPOSE 8080                            # 
# ENV SPRING_PROFILES_ACTIVE production  #
# COPY /jar/carshop-*.jar /car.jar       #
# ENTRYPOINT ["java","-jar","/car.jar"]  #
#########################################

no logget 
can browse the store's resources

User -> lukasz.bak@interiowy.pl Pass-> lukasz
can buy

User , moderator -> bbzzyyczczeek@interia.pl    pass -> lukasz
can buy,add and remove parts

User-> moderator -> admin bbzzyyczczeek@interia.pl pass -> lukasz
can buy,add and remove parts, remove accounts

You need to enter login and  password  to MYsql database application.properties.
To run application 

-----------------------------------------------------------------------------
VADIN
# git clone -b Vaadin https://github.com/LukaszBakJVM/CarShop
# maven clean
# maven install 
# java -jar /file path

or 

Dockerfile

################################################
#   FROM   ............                        #
#   EXPOSE 8080                                # 
#   COPY /carshop/carshop-*.jar /car.jar       #
#   ENTRYPOINT ["java","-jar","/car.jar"]      #
################################################


# docker run -d -p yourPort:8080   --restart=unless-stopped --name yourAppName carshop
