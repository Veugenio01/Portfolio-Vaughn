/**
 * @author Ana Boccanfuso
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.UUID;

public class InternshipPostTest {
    private InternshipList internships = InternshipList.getInstance();
    private ArrayList<InternshipPost> internshipList = internships.getInternships();
    private Student student = new Student("Ana", "Boccanfuso", "anaboca", "password", 2024, "ana@gmail.com", "somewhere", "0123456789", 4.0, true);

    @Before
    public void setup() {
        internshipList.clear();
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(Skill.JAVA);
        skills.add(Skill.PYTHON);
        internshipList.add(new InternshipPost("Google", "Software Development Intern", "coding things", "Mountain View, CA", skills, "May 2022", "August 2022", false, true, 1000, 2500));
        internshipList.add(new InternshipPost("Microsoft", "Application Design Intern", "design things", "Seattle, WA", skills, "Jan 2022", "December 2022", true, true, 2000, 3000));
        internshipList.get(0).addApplicant(student);
        DataWriter.saveInternshipPosts();
    }

    @After
    public void tearDown() {
        InternshipList.getInstance().getInternships().clear();
        DataWriter.saveInternshipPosts();
    }

    @Test
    public void testAddApplicantDuplicate() {
        internshipList.get(0).addApplicant(student);
        assertEquals(1, internshipList.get(0).getApplicants().size());
    }

    @Test
    public void testAddApplicantValid() {
        internshipList.get(0).addApplicant(new Student("Adam", "Steger", "asteger", "password", 2024, "adam@gmail.com", "address", "phone", 4.0, true));
        assertEquals(2, internshipList.get(0).getApplicants().size());
    }

    @Test
    public void testAddApplicantEmpty() {
        internshipList.get(0).addApplicant(new Student("", "", "", "", 0, "", "", "", 0.0, true));
        assertEquals(1, internshipList.get(0).getApplicants().size());
    }

    @Test
    public void testAddApplicantNull() {
        internshipList.get(0).addApplicant(null);
        assertEquals(1, internshipList.get(0).getApplicants().size());
    }

}