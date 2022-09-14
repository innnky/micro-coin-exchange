FROM mysql/mysql-server:8.0.30

MAINTAINER lengleng(wangiegie@gmail.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./coin.sql /docker-entrypoint-initdb.d

COPY ./coin_codegen.sql /docker-entrypoint-initdb.d

COPY ./coin_config.sql /docker-entrypoint-initdb.d

COPY ./coin_job.sql /docker-entrypoint-initdb.d
