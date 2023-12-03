# CarShop  in progress

https://carshop.freeddns.org


You need to enter login and  password in application.properties.
To run application 
clon https://github.com/LukaszBakJVM/CarShop
maven clean
maven install 
java - jar /file path

or 

Dockerfile

################################################
#   FROM   ............                        #
#   EXPOSE 8080                                # 
#   COPY /carshop/carshop-*.jar /car.jar       #
#   ENTRYPOINT ["java","-jar","/car.jar"]      #
################################################


docker run -d -p yourPort:8080   --restart=unless-stopped --name yourAppName carshop
