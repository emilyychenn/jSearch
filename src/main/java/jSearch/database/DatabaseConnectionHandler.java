package jSearch.database;

import jSearch.models.Applicant;
import jSearch.models.JobPositionCompensation;
import jSearch.models.Message;
import jSearch.models.SpecializationInfo;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseConnectionHandler {
    private static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String POSTGRESQL_USER = "postgres";
    private static final String POSTGRESQL_PASSWORD = "password";

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection conn = null;

    /**
     * Create database connection using try-with-resource pattern.
     */
    public DatabaseConnectionHandler() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    POSTGRESQL_URL, POSTGRESQL_USER, POSTGRESQL_PASSWORD);
            conn.setAutoCommit(false);

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public JobPositionCompensation[] getPositionsWithSalary(int minSalary) {
        ArrayList<JobPositionCompensation> result = new ArrayList<>();

        try {
            String query = "SELECT company_id, position_title, salary FROM job_position_compensation WHERE salary > ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, minSalary);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JobPositionCompensation model = new JobPositionCompensation((java.util.UUID) rs.getObject("company_id"),
                        rs.getString("position_title"),
                        rs.getInt("salary"));
                result.add(model);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new JobPositionCompensation[result.size()]);
    }

    public Object[] getFieldFromApplicant(String columnName) {
        Object[] result;

        switch (columnName) {
            case "applicant_id":
                result = getApplicantIDFromApplicant();
                break;
            case "applicant_name":
                result = getApplicantNameFromApplicant();
                break;
            case "applicant_phone":
                result = getApplicantPhoneFromApplicant();
                break;
            case "applicant_email":
                result = getApplicantEmailFromApplicant();
                break;
            case "spec_id":
                result = getSpecIDFromApplicant();
                break;
            case "supervisor_id":
                result = getSupervisorIDFromApplicant();
                break;
            case "university_name":
                result = getUniversityNameFromApplicant();
                break;
            default:
                result = new Object[] { columnName + " is an invalid column name." };
                break;
        }

        return result;
    }

    public UUID[] getApplicantIDFromApplicant() {
        ArrayList<UUID> result = new ArrayList<>();
        try {
            String query = "SELECT applicant_id FROM applicant";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UUID applicant_id = (java.util.UUID) rs.getObject("applicant_id");
                result.add(applicant_id);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new UUID[result.size()]);
    }

    public String[] getApplicantNameFromApplicant() {
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT applicant_name FROM applicant";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String applicant_name = rs.getString("applicant_name");
                result.add(applicant_name);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    public String[] getApplicantPhoneFromApplicant() {
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT applicant_phone FROM applicant";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String applicant_phone = rs.getString("applicant_phone");
                result.add(applicant_phone);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    public String[] getApplicantEmailFromApplicant() {
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT applicant_email FROM applicant";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String applicant_email = rs.getString("applicant_email");
                result.add(applicant_email);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    public UUID[] getSpecIDFromApplicant() {
        ArrayList<UUID> result = new ArrayList<>();
        try {
            String query = "SELECT spec_id FROM applicant";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UUID spec_id = (java.util.UUID) rs.getObject("spec_id");
                result.add(spec_id);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new UUID[result.size()]);
    }

    public UUID[] getSupervisorIDFromApplicant() {
        ArrayList<UUID> result = new ArrayList<>();
        try {
            String query = "SELECT supervisor_id FROM applicant";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UUID supervisor_id = (java.util.UUID) rs.getObject("supervisor_id");
                result.add(supervisor_id);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new UUID[result.size()]);
    }

    public String[] getUniversityNameFromApplicant() {
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT university_name FROM applicant";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String university_name = rs.getString("university_name");
                result.add(university_name);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    public SpecializationInfo[] getSpecializationFromApplicant(String applicant_email) {
        ArrayList<SpecializationInfo> result = new ArrayList<>();

        try {
            String query = "SELECT major, minor, is_honours, degree_type FROM specialization_info, applicant " +
                    "WHERE specialization_info.spec_id = applicant.spec_id AND applicant.applicant_email = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, applicant_email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SpecializationInfo model = new SpecializationInfo(rs.getString("major"),
                        rs.getString("minor"),
                        rs.getBoolean("is_honours"),
                        rs.getString("degree_type"));
                result.add(model);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new SpecializationInfo[result.size()]);
    }

    public Pair[] getNumberDistinctApplicantsAndMinGradYear() {
        ArrayList<Pair> result = new ArrayList<>();

        try {
            String query = "SELECT COUNT(distinct applicant_id), MIN(graduation_year) FROM attends";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pair model = new Pair<String, Integer>(rs.getString("count"),
                        rs.getInt("min"));
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new Pair[result.size()]);
    }

    public Pair[] getNumberApplicantsPerJobPosition() {
        ArrayList<Pair> result = new ArrayList<>();

        try {
            String query = "SELECT position_id, COUNT(applicant_id) FROM application_made GROUP BY position_id";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pair model = new Pair<String, Integer>(rs.getString("position_id"),
                        rs.getInt("count"));
                result.add(model);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Pair[result.size()]);
    }

    public String[] getJobPositionsAllApplicantsAppliedTo() {
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT position_title FROM job_position_belongs_to J WHERE NOT EXISTS ((SELECT A.applicant_id FROM Applicant A) EXCEPT (SELECT AM.applicant_id FROM Application_MADE AM WHERE AM.position_id = J.position_id))";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String position_title = rs.getString("position_title");
                result.add(position_title);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    public Applicant[] getAllApplicants() {
        ArrayList<Applicant> result = new ArrayList<>();

        try {
            String query = "SELECT * FROM applicant ";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Applicant model = new Applicant((java.util.UUID) rs.getObject("applicant_id"),
                        rs.getString("applicant_name"),
                        rs.getString("applicant_phone"),
                        rs.getString("applicant_email"),
                        (java.util.UUID) rs.getObject("spec_id"),
                        (java.util.UUID) rs.getObject("supervisor_id"),
                        rs.getString("university_name"));
                result.add(model);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Applicant[result.size()]);
    }

    public Message insertApplicant(Applicant app) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO applicant(applicant_id, applicant_name, applicant_phone, applicant_email, spec_id, supervisor_id, university_name) "
                            +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setObject(1, app.applicant_id, java.sql.Types.OTHER);
            ps.setString(2, app.applicant_name);
            ps.setString(3, app.applicant_phone);
            ps.setString(4, app.applicant_email);
            ps.setObject(5, app.spec_id, java.sql.Types.OTHER);
            ps.setObject(6, app.supervisor_id, java.sql.Types.OTHER);
            ps.setString(7, app.university_name);

            int update = ps.executeUpdate();
            System.out.println("update value: " + update);

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return new Message("OK");
    }

    public Message updateApplicant(Applicant app) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE applicant SET applicant_id = ?, applicant_name = ?, applicant_phone = ?, applicant_email = ?, spec_id = ?, supervisor_id = ?, university_name = ? WHERE applicant_id = ?");
            ps.setObject(1, app.applicant_id, java.sql.Types.OTHER);
            ps.setString(2, app.applicant_name);
            ps.setString(3, app.applicant_phone);
            ps.setString(4, app.applicant_email);
            ps.setObject(5, app.spec_id, java.sql.Types.OTHER);
            ps.setObject(6, app.supervisor_id, java.sql.Types.OTHER);
            ps.setString(7, app.university_name);
            ps.setObject(8, app.applicant_id, java.sql.Types.OTHER);

            int update = ps.executeUpdate();
            System.out.println("update value: " + update);

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return new Message("OK");
    }

    public Applicant[] getApplicants() {
        List<Applicant> result = new ArrayList<>();

        try {
            String query = "SELECT * FROM applicant";
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Applicant model = new Applicant((java.util.UUID) rs.getObject("applicant_id"),
                        rs.getString("applicant_name"),
                        rs.getString("applicant_phone"),
                        rs.getString("applicant_email"),
                        (java.util.UUID) rs.getObject("spec_id"),
                        (java.util.UUID) rs.getObject("supervisor_id"),
                        rs.getString("university_name"));
                result.add(model);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Applicant[result.size()]);
    }

    public Message deleteApplicant(String id) {
        try {
            UUID id2 = UUID.fromString(id);

            String query = "DELETE FROM applicant WHERE applicant_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id2, java.sql.Types.OTHER);

            int rs = ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return new Message("OK");
    }

    private void rollbackConnection() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
