
FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

COPY ../fastwebserver_with_cicd /home/custom-application/

RUN ln -sf /dev/stdout /home/custom-application/fastwebserver_with_cicd/server.logs

EXPOSE 8080

VOLUME application-storage:/home/custom-application

CMD ["./fastwebserver-runner.sh", ">", "server.logs", "2>&1"]
