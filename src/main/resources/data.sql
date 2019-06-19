DROP TABLE IF EXISTS matchedrss;

CREATE TABLE matchedrss (
  id INT AUTO_INCREMENT  PRIMARY KEY
);

DROP TABLE IF EXISTS rssdata;

CREATE TABLE rssdata (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  header VARCHAR(250) NOT NULL,
  link VARCHAR(250) NOT NULL,
  topic VARCHAR(50) NOT NULL,
  matched_id INT,
  foreign key (matched_id) references matchedrss(id)
);

