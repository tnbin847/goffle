DROP TABLE IF EXISTS USERS CASCADE;

CREATE TABLE USERS (
    ID                      BIGINT                  GENERATED BY DEFAULT AS IDENTITY,
    EMAIL                   VARCHAR(40)             NOT NULL,
    PASSWORD                VARCHAR(255)            NOT NULL,
    NAME                    VARCHAR(12)             NOT NULL,
    ENABLED_AT              TINYINT(1)              NOT NULL,
    LOGIN_TYPE              VARCHAR(5)              NOT NULL,
    USE_YN                  CHAR(1)                 NOT NULL,
    DELETE_YN               CHAR(1)                 NOT NULL,
    CREATED_DATETIME        DATETIME                NOT NULL DEFAULT NOW(),
    UPDATED_DATETIME        DATETIME                NOT NULL,
    PRIMARY KEY (ID)
);


DROP TABLE IF EXISTS USER_ROLES CASCADE;

CREATE TABLE USER_ROLES (
    USER_ROLE_ID            BIGINT                  NOT NULL AUTO_INCREMENT COMMENT '사용자프로필번호',
    USER_ID                 BIGINT                  NOT NULL,
    ROLE                    VARCHAR(20)             NOT NULL,
    USE_YN                  CHAR(1)                 NOT NULL,
    CREATED_DATETIME        DATETIME                NOT NULL DEFAULT NOW(),
    UPDATED_DATETIME        DATETIME                NOT NULL,
    PRIMARY KEY (USER_ROLE_ID)
);


DROP TABLE IF EXISTS USER_PROFILES CASCADE;

CREATE TABLE USER_PROFILES (
	USER_PROFILE_ID			BIGINT					NOT NULL AUTO_INCREMENT COMMENT '사용자프로필번호',
	USER_ID					BIGINT					NOT NULL COMMENT '사용자번호',
	PROFILE_IMAGE_PATH		VARCHAR(255)			NOT NULL DEFAULT 'NONE' COMMENT '프로필이미지 파일저장경로',
	PROFILE_IMAGE_NAME		VARCHAR(250)			NOT NULL DEFAULT 'NONE' COMMENT '프로필이미지 파일명',
	PROFILE_IMAGE_SIZE		BIGINT					NOT NULL DEFAULT 0 COMMENT '프로필이미지 크기',
	UNIV					VARCHAR(50)				NOT NULL DEFAULT 'NONE' COMMENT '출신 대학교명',
	CITY					VARCHAR(25)				NOT NULL DEFAULT 'NONE' COMMENT '거주 중인 도시명',
	CREATED_DATETIME		DATETIME				NOT NULL DEFAULT NOW() COMMENT '프로필 등록일자',
	UPDATED_DATETIME		DATETIME				NOT NULL COMMENT '프로필변경일자',
	PRIMARY KEY (USER_PROFILE_ID)
) ENGINE = INNODB DEFAULT CHARSET = UTF8MB4 COMMENT '사용자프로필테이블';


DROP TABLE IF EXISTS TEAMS CASCADE;

CREATE TABLE TEAMS (
	TEAM_ID					BIGINT					NOT NULL AUTO_INCREMENT COMMENT '팀번호',
	TEAM_NAME				VARCHAR(100)			NOT NULL COMMENT '팀명',
	INTRODUCTION			VARCHAR(150)			NOT NULL COMMENT '팀소개글(50자이내)',
	CREATED_DATETIME		DATETIME				NOT NULL DEFAULT NOW() COMMENT '팀정보 등록일자',
	UPDATED_DATETIME		DATETIME				NOT NULL COMMENT '팀정보 변경일자',
	USE_YN					CHAR(1)					NOT NULL DEFAULT 'Y' COMMENT '사용여부',
	DELETE_YN				CHAR(1)					NOT NULL DEFAULT 'N' COMMENT '팀정보 삭제여부',
	PRIMARY KEY (TEAM_ID)
) ENGINE = INNODB DEFAULT CHARSET = UTF8MB4 COMMENT '팀정보테이블';


DROP TABLE IF EXISTS TEAM_USERS CASCADE;

CREATE TABLE TEAM_USERS (
	TEAM_ID					BIGINT					NOT NULL COMMENT '팀번호',
	USER_ID					BIGINT					NOT NULL COMMENT '사용자번호',
	IS_CAPTAIN				CHAR(1)					NOT NULL COMMENT '팀장여부',
	CREATED_DATETIME		DATETIME				NOT NULL DEFAULT NOW() COMMENT '팀원등록일자',
	UPDATED_DATETIME		DATETIME				NOT NULL COMMENT '팀원변경일자'
) ENGINE = INNODB DEFAULT CHARSET = UTF8MB4 COMMENT '팀원테이블';