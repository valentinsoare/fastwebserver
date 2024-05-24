FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

RUN mkdir -p /home/custom-application/fastwebserver

COPY . /home/custom-application/fastwebserver/

RUN ln -sf /dev/stdout /home/custom-application/fastwebserver/server.logs

EXPOSE 8080

VOLUME application-storage:/home/custom-application

WORKDIR /home/custom-application/fastwebserver

CMD ["./fastwebserver-runner.sh", ">", "server.logs", "2>&1"]
