
FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

COPY ../fastwebserver_with_cicd/* /home/custom-application/fastwebserver/

RUN ln -sf /dev/stdout /home/custom-application/fastwebserver/server.logs \
    && sudo su fastwebserver

EXPOSE 8080

VOLUME application-storage:/home/custom-application

CMD ["./fastwebserver-runner.sh", ">", "server.logs", "2>&1"]
