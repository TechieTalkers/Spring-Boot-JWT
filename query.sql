

---------Table creation SQL QUERY----------

CREATE TABLE `userdetails` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
)

----------Insertion querey----------

INSERT INTO `userdetails` (`username`,`password`) VALUES("techietalker", "password");
