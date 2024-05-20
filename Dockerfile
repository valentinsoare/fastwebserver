FROM fedora:39

LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN sed -i 's/# %wheel/%wheel/g' /etc/sudoers \
    && useradd -c 'fastwebserver user application' -m -s /bin/bash fastwebserver \
    && usermod -aG wheel fastwebserver \
    && dnf install passwd \
                   java-17-openjdk.x86_64 -y \
    && echo "default" | passwd fastwebserver --stdin \
    && chown -R fastwebserver:fastwebserver /home/fastwebserver \
    && chmod 750 /home/fastwebserver/

RUN ln -sf /dev/stdout /home/fastwebserver/server.logs \
    && sudo su fastwebserver

EXPOSE 8080

VOLUME webserver-storage:/home/fastwebserver

WORKDIR /home/fastwebserver/

COPY target/fastwebserver .

CMD ["/home/fastwebserver/fastwebserver-runner.sh", ">", "server.logs", "2>&1"]
