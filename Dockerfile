FROM ubuntu:latest
LABEL authors="fandream"

ENTRYPOINT ["top", "-b"]