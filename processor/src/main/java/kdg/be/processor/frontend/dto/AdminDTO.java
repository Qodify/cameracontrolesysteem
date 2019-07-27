package kdg.be.processor.frontend.dto;

public class AdminDTO {
    private long id;

    private String username;
    private String password;

    public void setId(long id) {
        this.id = id;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    private String[] roles;

    public AdminDTO() {
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public AdminDTO(String name, String email, String username, String password, String[] roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public String[] getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
