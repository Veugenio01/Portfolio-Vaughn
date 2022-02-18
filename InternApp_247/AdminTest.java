/**
 * @author Ana Boccanfuso
 * @author Adam Sanfacon
 */
import org.junit.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class AdminTest {
    private InternshipList internshipList = InternshipList.getInstance();
    private ArrayList<InternshipPost> internshipPosts = internshipList.getInternships();
    private Admin testAdmin = new Admin("asteger", "password");
    private Student student = new Student("Ana", "Boccanfuso", "anaboca", "password", 2024, "ana@gmail.com", "somewhere", "8031234567", 4.0, true);
    private Employer employer = new Employer("Google", "password");



    @Before
    public void setup() {
        internshipPosts.clear();
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(Skill.C);
        skills.add(Skill.HTML);
        internshipPosts.add(new InternshipPost("Google", "Software Engineer Intern", "work", "cali", skills, "2021", "2022", true, true, 1000, 3000));
        student.getReviews().add(new Review("asteger", 5, "good"));
        employer.getReviews().add(new Review("anaboca", 5, "nice"));
        DataWriter.saveEmployers();
        DataWriter.saveStudents();
        DataWriter.saveInternshipPosts();
    }

    @After 
    public void tearDown() {
        InternshipList.getInstance().getInternships().clear();
        student.getReviews().clear();
        employer.getReviews().clear();
        DataWriter.saveEmployers();
        DataWriter.saveStudents();
        DataWriter.saveInternshipPosts();
    }

    @Test
    public void testAddPostValid() {
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(Skill.SQL);
        skills.add(Skill.CPP);
        testAdmin.addPost(new InternshipPost("Meta", "NFT salesman or something", "Make magic money for pixels", "Anywhere", skills, "April 2022", "December 2022", true, true, 25000, 40000));
        assertTrue(internshipList.haveInternshipPost("Meta", "NFT salesman or something"));
    }

    @Test
    public void testAddPostDuplicate() {
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(Skill.C);
        skills.add(Skill.HTML);
        testAdmin.addPost(new InternshipPost("Google", "Software Engineer Intern", "work", "cali", skills, "2021", "2022", true, true, 1000, 3000));
        assertEquals(1, internshipList.getInternships().size());
    }

    @Test
    public void testAddPostEmpty() {
        assertFalse(testAdmin.addPost(new InternshipPost("","","","", new ArrayList<Skill>(), "","",false,false,0,0)));
    }
    
    @Test
    public void testAddPostNull() {
        testAdmin.addPost(null);
        assertFalse(testAdmin.addPost(null));
    }

    @Test
    public void testDeletePostValid() {
        InternshipPost current = internshipList.getInternships().get(0);
        testAdmin.deletePost(current);
        assertEquals(0, internshipList.getInternships().size());
    }

    @Test
    public void testDeletePostInValid() {
        testAdmin.deletePost(new InternshipPost("Meta", "NFT salesman or something", "Make magic money for pixels", "Anywhere", null, "April 2022", "December 2022", true, true, 25000, 40000));
        assertEquals(1, internshipList.getInternships().size());
    }

    @Test
    public void testDeletePostNull() {
        testAdmin.deletePost(null);
        assertEquals(1, internshipList.getInternships().size());
    }

    @Test
    public void testDeleteStudentReviewValid() {
        testAdmin.deleteStudentReview(student, student.getReviews().get(0));
        assertEquals(0, student.getReviews().size());
    }

    @Test
    public void testDeleteStudentReviewInValid() {
        testAdmin.deleteStudentReview(student, new Review("Google", 3, "okay"));
        assertEquals(1, student.getReviews().size());
    }

    @Test
    public void testDeleteStudentReviewEmpty() {
        testAdmin.deleteStudentReview(student, new Review("", 0, ""));
        assertEquals(1, student.getReviews().size());
    }

    @Test
    public void testDeleteStudentReviewNull() {
        testAdmin.deleteStudentReview(student, null);
        assertEquals(1, student.getReviews().size());
    }

    @Test
    public void testDeleteEmployerReviewValid() {
        testAdmin.deleteEmployerReview(employer, employer.getReviews().get(0));
        assertEquals(0, employer.getReviews().size());
    }

    @Test
    public void testDeleteEmployerReviewInValid() {
        testAdmin.deleteEmployerReview(employer, new Review("asteger", 3, "okay"));
        assertEquals(1, student.getReviews().size());
    }

    @Test
    public void testDeleteEmployerReviewEmpty() {
        testAdmin.deleteEmployerReview(employer, new Review("", 0, ""));
        assertEquals(1, employer.getReviews().size());
    }

    @Test
    public void testDeleteEmployerReviewNull() {
        testAdmin.deleteEmployerReview(employer, null);
        assertEquals(1, employer.getReviews().size());
    }

}
