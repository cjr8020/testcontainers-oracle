# testcontainers-oracle

explore using `testcontainers` with Oracle-XE.

this sample uses `oracleinanutshell/oracle-xe-11g` image.

```text
$ docker pull oracleinanutshell/oracle-xe-11g
Using default tag: latest
latest: Pulling from oracleinanutshell/oracle-xe-11g
6cf436f81810: Pull complete
987088a85b96: Pull complete
b4624b3efe06: Pull complete
d42beb8ded59: Pull complete
15522cc0fb47: Pull complete
f747bf1d551d: Pull complete
Digest: sha256:8b740e77d4b90add693fedb22938f340821e89665fb58ecaeeb0dace853b9ee5
Status: Downloaded newer image for oracleinanutshell/oracle-xe-11g:latest


$ docker run -d -p 49161:1521 --name oracle-xe-11g oracleinanutshell/oracle-xe-11g
  1604b1128f52a5f9019454200a3afdc4d1eef17fe240c4768d291b408b24f869
  

$ docker logs oracle-xe-11g

$ docker exec -it oracle-xe-11g bash -c "source /home/oracle/.bashrc; bash"

$ docker inspect oracle-xe-11g | grep '"IPAddress"' | head -n 1
              "IPAddress": "172.17.0.2"
              
            
$ docker stop oracle-xe-11g

$ docker container ls -a

$ docker container rm b692777a07fd

```
            

### Flyway

#### Flyway Enterprise or Oracle upgrade required.
     
 ```text
 org.flywaydb.core.internal.exception.FlywayEnterpriseUpgradeRequiredException: 
 Flyway Enterprise or Oracle upgrade required: Oracle 11 is past regular support by Oracle and no longer supported by Flyway Open Source or Pro, but still supported by Flyway Enterprise.
 ```
 
 ### Liquibase
 
 No issues with Oracle 11g
 
 except getting the oracle sql syntax right is a pain in the ass