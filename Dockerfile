FROM gradle

RUN mkdir /code
WORKDIR /code

ADD . /code

#构建应用
RUN gradle build  -x test\
        #拷贝编译结果到指定目录
        && mv build/libs/*.jar /app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","/app.jar"]
