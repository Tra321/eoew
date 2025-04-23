# 宿舍管理系统

## 数据库设置指南

### 1. 数据库安装

1. 确保已安装 MySQL 数据库服务器（推荐版本：MySQL 8.0+）
2. 确保 MySQL 服务正在运行

### 2. 创建数据库

有两种方式可以创建数据库：

#### 方式一：使用SQL脚本

1. 打开 MySQL 命令行客户端：
   ```bash
   mysql -u root -p
   ```

2. 输入您的 MySQL root 密码

3. 运行以下命令来执行 SQL 脚本：
   ```sql
   source /path/to/dormitory_db.sql
   ```
   注意：请将 `/path/to/` 替换为脚本实际所在的路径。

#### 方式二：使用数据库管理工具

1. 打开 MySQL Workbench、Navicat、phpMyAdmin 等管理工具
2. 连接到您的 MySQL 服务器
3. 创建新的查询，并将 dormitory_db.sql 文件中的内容复制粘贴进去
4. 执行查询

### 3. 配置数据库连接

1. 打开 `config/database.properties` 文件
2. 根据您的 MySQL 配置修改以下参数：
   ```properties
   jdbc.url=jdbc:mysql://localhost:3306/attendance_system?useSSL=false&serverTimezone=UTC
   jdbc.username=root
   jdbc.password=password
   ```
   
   - 修改 `jdbc.url` 中的主机名、端口和数据库名（如有必要）
   - 将 `jdbc.username` 和 `jdbc.password` 修改为您的 MySQL 用户名和密码

### 4. 数据库表说明

系统包含以下数据表：

1. **admin** - 管理员信息表
   - admin_id: 管理员ID (主键)
   - admin_name: 管理员姓名
   - admin_password: 管理员密码 (MD5加密)
   - phone: 电话号码
   - email: 电子邮箱

2. **employee** - 员工信息表
   - employee_id: 员工ID (主键)
   - employee_name: 员工姓名
   - employee_password: 员工密码 (MD5加密)
   - department: 部门
   - position: 职位
   - phone: 电话号码
   - email: 电子邮箱
   - hire_date: 入职日期

3. **attendance_record** - 考勤记录表
   - record_id: 记录ID (主键, 自增)
   - employee_id: 员工ID (外键)
   - check_in_time: 签到时间
   - check_out_time: 签退时间
   - attendance_status: 考勤状态 (NORMAL-正常, LATE-迟到, EARLY_LEAVE-早退, ABSENT-缺勤)
   - remarks: 备注

4. **leave_request** - 请假申请表
   - request_id: 申请ID (主键, 自增)
   - employee_id: 员工ID (外键)
   - start_time: 开始时间
   - end_time: 结束时间
   - leave_type: 请假类型 (SICK-病假, PERSONAL-事假, VACATION-休假)
   - reason: 请假原因
   - status: 状态 (PENDING-待审批, APPROVED-已批准, REJECTED-已拒绝)
   - approver_remarks: 审批备注

### 5. 账号信息

数据库中预设了以下测试账号：

#### 管理员账号
- 账号：admin001 密码：123456
- 账号：admin002 密码：123456

#### 员工账号
- 账号：emp001 密码：123456
- 账号：emp002 密码：123456
- 账号：emp003 密码：123456
- 账号：emp004 密码：123456

### 6. 故障排除

如果遇到数据库连接问题，请检查：

1. MySQL 服务是否正在运行
2. 数据库连接参数是否正确
3. MySQL 用户是否有足够的权限
4. 防火墙是否阻止了数据库连接
5. JDBC 驱动是否存在于项目的 libs 目录中

如需进一步帮助，请联系系统管理员。 