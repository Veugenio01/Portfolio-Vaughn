import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class StudentTest {
    private InternshipList internshipList = InternshipList.getInstance();
    private ArrayList<InternshipPost> internshipPosts = internshipList.getInternships();
    private Student student = new Student("Ana", "Boccanfuso", "anaboca", "password", 2024, "ana@gmail.com", "somewhere", "8031234567", 4.0, true);


    @Before
    public void setup() {
        internshipPosts.clear();
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(Skill.C);
        skills.add(Skill.HTML);
        internshipPosts.add(new InternshipPost("Google", "Software Engineer Intern", "work", "cali", skills, "2021", "2022", true, true, 1000, 3000));
        DataWriter.saveInternshipPosts();
        }

    @After 
    public void tearDown() {
        InternshipList.getInstance().getInternships().clear();;
        DataWriter.saveInternshipPosts();
    }

    @Test
    public void testApplyValid() {
        student.apply(internshipList.getInternships().get(0));
        int size = internshipList.getInternships().get(0).getApplicants().size();
        assertEquals(1, size);
    }
     // cannot apply to a null, invalid, or empty internship post because it won't be listed
}
