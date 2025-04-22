package dao;

import model.StaffMember;

public interface StaffMemberDao {
    StaffMember login(StaffMember staffMember);
    void addStaffMember(StaffMember staffMember);
    void deleteStaffMember(String staffID);
    void updateStaffMember(StaffMember staffMember);
    StaffMember selectOneStaffMember(String staffID);
    void selectAllStaffMember();
}