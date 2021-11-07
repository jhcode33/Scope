#!/usr/bin/env bash
# start.sh
# 서버 구동을 위한 스크립트

# 절대경로를 이용하여 profile.sh 경로 찾은 후 import
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

# 현재 프로젝트 경로 지정
REPOSITORY=/home/ubuntu/scope
IDLE_PORT=$(find_idle_port)

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

# 새로운 jar file 덮어쓰기
cp $REPOSITORY/zip/*.jar $REPOSITORY

echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -S $REPOSITORY/*.jar | head -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."

IDLE_PROFILE=$(find_idle_profile)

cd $REPOSITORY

sudo docker run -it --name "$IDLE_PROFILE" -d -e active=$IDLE_PROFILE -p $IDLE_PORT:$IDLE_PORT -v $(pwd):/scope scope