select * from lykey order by email;
select * from lykey_lyrics order by idx desc;

alter table lykey_lyrics
add CONSTRAINT email FOREIGN key (email)
REFERENCES lykey(email) on DELETE CASCADE;
alter table lykey_lyrics drop CONSTRAINT email;

delete from lykey_lyrics;
drop sequence lykey_lyrics_idx_seq;
create sequence lykey_lyrics_idx_seq;
commit;
delete from lykey_lyrics where idx = 9;