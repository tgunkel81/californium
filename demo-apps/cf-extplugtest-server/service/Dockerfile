#/*******************************************************************************
# * Copyright (c) 2020 Bosch.IO GmbH and others.
# * 
# * All rights reserved. This program and the accompanying materials
# * are made available under the terms of the Eclipse Public License v2.0
# * and Eclipse Distribution License v1.0 which accompany this distribution.
# * 
# * The Eclipse Public License is available at
# *    http://www.eclipse.org/legal/epl-v20.html
# * and the Eclipse Distribution License is available at
# *    http://www.eclipse.org/org/documents/edl-v10.html.
# * 
# * Contributors:
# *    Achim Kraus (Bosch.IO GmbH) - initial script
# ******************************************************************************/
#
# usage: docker build . -t <tag> service/Dockerfile

FROM eclipse-temurin:17-jre-focal

RUN mkdir /opt/app
COPY ./service/build ./CaliforniumReceivetest3.properties /opt/app/
COPY ./target/cf-extplugtest-server-4.0.0-SNAPSHOT.jar /opt/app/cf-extplugtest-server.jar

#EXPOSE 5683/udp
#EXPOSE 5683/tcp
#EXPOSE 5684/udp
#EXPOSE 5684/tcp
EXPOSE 5783/udp
#EXPOSE 5783/tcp
EXPOSE 5784/udp
#EXPOSE 5784/tcp
EXPOSE 5884/udp
EXPOSE 5884/tcp
EXPOSE 8080/tcp

WORKDIR /opt/app
CMD ["java", "-XX:+UseZGC", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=80", "-jar", "./cf-extplugtest-server.jar", "--no-loopback", "--no-tcp", "--no-plugtest", "--benchmark", "--diagnose", "--k8s-dtls-cluster", ":5784;:5884;5884", "--k8s-monitor", ":8080", "--k8s-restore", ":5884"]
