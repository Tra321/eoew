package model;

public class Account {
    private int id;
    private String accountName;    // 账号名称
    private String username;       // 用户名
    private String password;       // 密码
    private String category;       // 分类（如：社交、工作、学习等）
    private String description;    // 描述
    private String createTime;     // 创建时间
    private String updateTime;     // 更新时间
    private int userId;           // 所属用户ID

    // 构造函数
    public Account() {}

    public Account(String accountName, String username, String password, String category, 
                  String description, int userId) {
        this.accountName = accountName;
        this.username = username;
        this.password = password;
        this.category = category;
        this.description = description;
        this.userId = userId;
    }

    // Getter和Setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
} 