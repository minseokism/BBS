-- 사용자
CREATE TABLE USER (
	USER_NO INTEGER      PRIMARY KEY AUTO_INCREMENTs, -- 회원번호
	ID      VARCHAR(100) UNIQUE NOT NULL, -- 아이디
	PWD     VARCHAR(100) NOT NULL, -- 비밀번호
	EMAIL   VARCHAR(100) UNIQUE NOT NULL, -- 이메일
	NAME    VARCHAR(50)  NOT NULL  -- 이름
);
			
-- 게시판
CREATE TABLE POST (
	POST_NO  INTEGER      NOT NULL, -- 게시글번호
	SUBJECT  VARCHAR(300) NOT NULL, -- 제목
	CONTENT  TEXT         NOT NULL, -- 내용
	REG_DATE DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP    NOT NULL, -- 등록일자
	MOD_DATE DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP    NOT NULL, -- 수정일자
	USER_NO  INTEGER      NOT NULL  -- 회원번호
);

-- 게시판 기본키
CREATE UNIQUE INDEX PK_POST
	ON POST ( -- 게시판
		POST_NO ASC -- 게시글번호
	);

-- 게시판
ALTER TABLE POST
	ADD
		CONSTRAINT PK_POST -- 게시판 기본키
		PRIMARY KEY (
			POST_NO -- 게시글번호
		);

-- 코멘트
CREATE TABLE COMMENT (
	COM_NO   INTEGER   NOT NULL, -- 코멘트번호
	POST_NO  INTEGER   NOT NULL, -- 게시글번호
	USER_NO  INTEGER   NOT NULL, -- 회원번호
	CONTENT  TEXT      NOT NULL, -- 내용
	REG_DATE DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL, -- 등록일자
	MOD_DATE DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL  -- 수정일자
);

-- 코멘트 기본키
CREATE UNIQUE INDEX PK_COMMENT
	ON COMMENT ( -- 코멘트
		COM_NO ASC -- 코멘트번호
	);

-- 코멘트
ALTER TABLE COMMENT
	ADD
		CONSTRAINT PK_COMMENT -- 코멘트 기본키
		PRIMARY KEY (
			COM_NO -- 코멘트번호
		);

-- 첨부사진
CREATE TABLE PHOTO (
	PHOTO_NO INTEGER       NOT NULL, -- 사진번호
	POST_NO  INTEGER       NOT NULL, -- 게시글번호
	ADDRESS  VARCHAR(1000) NOT NULL  -- 사진주소
);

-- 첨부사진 기본키
CREATE UNIQUE INDEX PK_PHOTO
	ON PHOTO ( -- 첨부사진
		PHOTO_NO ASC -- 사진번호
	);

-- 첨부사진
ALTER TABLE PHOTO
	ADD
		CONSTRAINT PK_PHOTO -- 첨부사진 기본키
		PRIMARY KEY (
			PHOTO_NO -- 사진번호
		);

-- 게시판
ALTER TABLE POST
	ADD
		CONSTRAINT FK_USER_TO_POST -- 사용자 -> 게시판
		FOREIGN KEY (
			USER_NO -- 회원번호
		)
		REFERENCES USER ( -- 사용자
			USER_NO -- 회원번호
		);

-- 코멘트
ALTER TABLE COMMENT
	ADD
		CONSTRAINT FK_POST_TO_COMMENT -- 게시판 -> 코멘트
		FOREIGN KEY (
			POST_NO -- 게시글번호
		)
		REFERENCES POST ( -- 게시판
			POST_NO -- 게시글번호
		);

-- 코멘트
ALTER TABLE COMMENT
	ADD
		CONSTRAINT FK_USER_TO_COMMENT -- 사용자 -> 코멘트
		FOREIGN KEY (
			USER_NO -- 회원번호
		)
		REFERENCES USER ( -- 사용자
			USER_NO -- 회원번호
		);

-- 첨부사진
ALTER TABLE PHOTO
	ADD
		CONSTRAINT FK_POST_TO_PHOTO -- 게시판 -> 첨부사진
		FOREIGN KEY (
			POST_NO -- 게시글번호
		)
		REFERENCES POST ( -- 게시판
			POST_NO -- 게시글번호
		);