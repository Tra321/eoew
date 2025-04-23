-- 创建数据库
CREATE DATABASE IF NOT EXISTS attendance_system;
USE attendance_system;

-- 创建班级表
CREATE TABLE IF NOT EXISTS class (
    classID VARCHAR(20) PRIMARY KEY,
    className VARCHAR(50) NOT NULL
);

-- 创建学生表
CREATE TABLE IF NOT EXISTS student (
    studentID VARCHAR(20) PRIMARY KEY,
    studentName VARCHAR(50) NOT NULL,
    studentPassword VARCHAR(50) NOT NULL,
    classID VARCHAR(20),
    studentTel VARCHAR(20),
    studentEmail VARCHAR(50),
    FOREIGN KEY (classID) REFERENCES class(classID)
);

-- 创建管理员表
CREATE TABLE IF NOT EXISTS admin (
    adminID VARCHAR(20) PRIMARY KEY,
    adminPassword VARCHAR(50) NOT NULL
);

-- 创建考勤记录表
CREATE TABLE IF NOT EXISTS attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    studentID VARCHAR(20) NOT NULL,
    attendanceDate DATE NOT NULL,
    checkInTime TIME,
    checkOutTime TIME,
    status VARCHAR(10) NOT NULL, -- 正常 / 迟到 / 缺勤 / 请假
    FOREIGN KEY (studentID) REFERENCES student(studentID)
);

-- 插入初始数据

-- 班级
INSERT INTO class (classID, className) VALUES
('CS2023001', '计算机科学与技术1班'),
('CS2023002', '计算机科学与技术2班'),
('SE2023001', '软件工程1班');

-- 管理员
INSERT INTO admin (adminID, adminPassword) VALUES
('admin', '123456');

-- 学生
INSERT INTO student (studentID, studentName, studentPassword, classID, studentTel, studentEmail) VALUES
('2023001001', '张三', '123456', 'CS2023001', '13800000001', 'zhangsan@example.com'),
('2023001002', '李四', '123456', 'CS2023001', '13800000002', 'lisi@example.com'),
('2023001003', '王五', '123456', 'CS2023002', '13800000003', 'wangwu@example.com'),
('2023001004', '赵六', '123456', 'SE2023001', '13800000004', 'zhaoliu@example.com');

-- 考勤记录
INSERT INTO attendance (studentID, attendanceDate, checkInTime, checkOutTime, status) VALUES
('2023001001', '2023-06-01', '08:00:00', '17:00:00', '正常'),
('2023001002', '2023-06-01', '08:30:00', '17:00:00', '迟到'),
('2023001003', '2023-06-01', '08:00:00', '17:00:00', '正常'),
('2023001004', '2023-06-01', NULL, NULL, '缺勤'),
('2023001001', '2023-06-02', '08:00:00', '17:00:00', '正常'),
('2023001002', '2023-06-02', '08:00:00', '17:00:00', '正常'),
('2023001003', '2023-06-02', NULL, NULL, '请假'),
('2023001004', '2023-06-02', '08:00:00', '17:00:00', '正常'); 