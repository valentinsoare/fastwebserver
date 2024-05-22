
FROM fedora:40
LABEL maintainer="Valentin Soare"

RUN dnf update -y && dnf install unzip zip maven -y

RUN curl -s "https://get.sdkman.io" | bash

RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install java 22.0.1-graal && sdk use java 22.0.1-graal"

RUN echo "source $HOME/.sdkman/bin/sdkman-init.sh" >> $HOME/.bashrc

CMD ["/bin/bash"]