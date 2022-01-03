DROP PROCEDURE IF EXISTS SP_CREATE__ENCRYPTION_LOGIC;

delimiter |
CREATE PROCEDURE SP_CREATE__ENCRYPTION_LOGIC()
BEGIN

    -- ENCRYPTION LOGIC TABLE CREATE
    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`encryption_logic`;

    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`encryption_logic`
    (
        `character`      VARCHAR(255)                        NOT NULL,
        `hash`        VARCHAR(255)                        NOT NULL,
        PRIMARY KEY (`hash`)
    ) ENGINE = INNODB
      DEFAULT CHARACTER SET = UTF8MB3;
	
	-- ENCRYPTION LOGIC TABLE INSERT VALUES
	INSERT INTO `CSCI5308_7_DEVINT`.`encryption_logic`(`character`,`hash`) VALUES ('a','xt'),('b','rs'),('c','fd'),('d','cx'),
														 ('e','af'),('f','aa'),('g','xx'),('h','cv'),
														 ('i','bv'),('j','jh'),('k','jk'),('l','yt'),
														 ('m','rw'),('n','qa'),('o','qx'),('p','ut'),
														 ('q','bu'),('r','br'),('s','df'),('t','po'),
														 ('u','io'),('v','lj'),('w','qw'),('x','qu'),
														 ('y','aq'),('z','dd'),('1','z'),('2','y'),
														 ('3','x'),('4','w'),('5','v'),('6','u'),
														 ('7','t'),('8','s'),('9','r'),('0','q'),
														 ('A','xta'),('B','rsb'),('C','fdc'),('D','cxd'),
														 ('E','ase'),('F','aaf'),('G','xxg'),('H','cvh'),
														 ('I','bvi'),('J','jhj'),('K','jkk'),('L','ytl'),
														 ('M','rwm'),('N','qan'),('O','qxo'),('P','utp'),
														 ('Q','buq'),('R','brr'),('S','dfs'),('T','pot'),
														 ('U','iou'),('V','ljv'),('W','qww'),('X','qux'),
														 ('Y','aqy'),('Z','ddz'),('!','~'),('@','-'),
														 ('#','{'),('$','}'),('%','|'),('^',']'),('&','!'),
														 ('-','/'),('+',':');
	
    
END
|

CALL SP_CREATE__ENCRYPTION_LOGIC();

SHOW PROCEDURE STATUS;