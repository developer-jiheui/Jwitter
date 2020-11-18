ALTER table bookmarks
ADD CONSTRAINT UC_bookmarks UNIQUE (user_id,twit_id);
