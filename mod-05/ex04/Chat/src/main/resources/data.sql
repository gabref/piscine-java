INSERT INTO chat.users (login, password)
VALUES
	('galves-f', 'intra42'),
	('gabref', 'github'),
	('gabre___', 'instagram'),
	('gabre535f', 'gmail'),
	('gabre', 'nickname');

INSERT INTO chat.chatrooms (name, owner)
VALUES
	('general', (SELECT id FROM chat.users WHERE login = 'galves-f')),
	('random', (SELECT id FROM chat.users WHERE login = 'galves-f')),
	('memes', (SELECT id FROM chat.users WHERE login = 'galves-f')),
	('musicBot', (SELECT id FROM chat.users WHERE login = 'galves-f')),
	('art', (SELECT id FROM chat.users WHERE login = 'galves-f'));

INSERT INTO chat.messages (author, chatroom, text, datetime)
VALUES
	((SELECT id FROM chat.users WHERE login = 'galves-f'), 1, 'This is general channel!', (SELECT NOW()::timestamp)),
	((SELECT id FROM chat.users WHERE login = 'galves-f'), 2, 'This is random channel!', (SELECT NOW()::timestamp)),
	((SELECT id FROM chat.users WHERE login = 'galves-f'), 3, 'This is memes channel!', (SELECT NOW()::timestamp)),
	((SELECT id FROM chat.users WHERE login = 'galves-f'), 4, 'This is musicBot channel!', (SELECT NOW()::timestamp)),
	((SELECT id FROM chat.users WHERE login = 'galves-f'), 5, 'This is art channel!', (SELECT NOW()::timestamp));
