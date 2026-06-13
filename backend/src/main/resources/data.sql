INSERT INTO developers (name, email, team) VALUES
('Ana Garcia', 'ana.garcia@dev.com', 'Backend'),
('Luis Martinez', 'luis.martinez@dev.com', 'Frontend'),
('Maria Lopez', 'maria.lopez@dev.com', 'Backend'),
('Carlos Rodriguez', 'carlos.rodriguez@dev.com', 'DevOps'),
('Sofia Hernandez', 'sofia.hernandez@dev.com', 'Frontend');

INSERT INTO metrics (developer_id, date, commits, tasks_completed, incidents_resolved, lines_added, lines_removed) VALUES
(1, '2026-01-15', 12, 5, 3, 450, 120),
(1, '2026-02-15', 15, 7, 4, 620, 180),
(1, '2026-03-15', 10, 6, 2, 380, 90),
(2, '2026-01-15', 8,  4, 2, 320, 80),
(2, '2026-02-15', 11, 5, 3, 480, 150),
(2, '2026-03-15', 9,  6, 1, 410, 100),
(3, '2026-01-15', 14, 8, 5, 560, 200),
(3, '2026-02-15', 13, 7, 4, 520, 170),
(3, '2026-03-15', 16, 9, 6, 640, 220),
(4, '2026-01-15', 6,  3, 8, 200, 50),
(4, '2026-02-15', 7,  4, 7, 250, 60),
(4, '2026-03-15', 5,  2, 9, 180, 40),
(5, '2026-01-15', 10, 6, 3, 400, 120),
(5, '2026-02-15', 12, 7, 4, 460, 140),
(5, '2026-03-15', 11, 8, 2, 430, 130);
