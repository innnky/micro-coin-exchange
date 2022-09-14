FROM moxm/java:1.8-full

RUN mkdir -p /coin-register

WORKDIR /coin-register

ARG JAR_FILE=target/coin-register.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8848

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD sleep 30; java -jar app.jar $JAVA_OPTS
