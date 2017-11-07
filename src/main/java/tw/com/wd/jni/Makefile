TARGET_DIR = /home/option0417/Dev/sharedLib
TARGET = libJNIService.so
INCLUDE_JAVA = /opt/jdk1.7.0_45/include
OPT_SHARED = -shared
OPT_WALL = -Wall
OPT_OPTIM = -O

all: $(TARGET)
	@echo done

$(TARGET):
	@echo Start building JNI library
	@gcc -shared -fpic -I${INCLUDE_JAVA} -I${INCLUDE_JAVA}/linux JNIService.c -o ${TARGET_DIR}/${TARGET}

clean:
	rm -f ./${TARGET}
	@echo clean done

EXE:
	@gcc -v -Wall -O -I${INCLUDE_JAVA} -I${INCLUDE_JAVA}/linux JNIService.c -o JNIService
	
