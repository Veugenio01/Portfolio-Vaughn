import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class ResumeTest {
    public Resume resume = new Resume(new ArrayList<Education>(), new Hashtable<Skill,Boolean>(), new Hashtable<String,Boolean>(), new ArrayList<WorkExperience>(), new ArrayList<Extracurricular>(), new ArrayList<Honor>());
    public ArrayList<Education> edu;    // tests adding/deleting of all arraylists 
    public Hashtable<Skill, Boolean> skills;    // tests adding/deleting of all hashtables

    @Before
    public void setup() {
        resume.addEducation(new Education("schoolTitle", "location", "major", 2022, true));
        resume.addSkill(Skill.C, true);
        edu = resume.getEducations();
        skills = resume.getSkills();
        DataWriter.saveResumes();
    }

    @After
    public void tearDown() {
        resume.getEducations().clear();
        resume.getSkills().clear();
        DataWriter.saveResumes();
    }

    @Test
    public void testAddEducationValid() {
        resume.addEducation(new Education("UofSC", "Columbia", "CS", 2025, true));
        assertEquals(2, resume.getEducations().size());
    }

    @Test
    public void testAddEducationDuplicate() {
        resume.addEducation(new Education("schoolTitle", "location", "major", 2022, true));
        assertEquals(1, resume.getEducations().size());
    }

    @Test
    public void testAddEducationEmpty() {
        resume.addEducation(new Education("", "", "", 0, true));
        assertEquals(1, resume.getEducations().size());
    }
    // in UI, cannot add "null" education, only empty

    @Test
    public void testRemoveEducationValid() {
        resume.removeEducation(resume.getEducations().get(0));
        assertEquals(0, resume.getEducations().size());
    }
    // in UI, cannot remove an eduation that isn't listed

    @Test
    public void testAddSkillValid() {
        resume.addSkill(Skill.PYTHON, true);
        assertEquals(2, resume.getSkills().size());
    }

    @Test
    public void testAddSkillDuplicate() {
        resume.addSkill(Skill.C, true);
        assertEquals(1, resume.getSkills().size());
    }
    // cannot add something that is not a skill (in enum)
    
    @Test
    public void testRemoveSkillValid() {
        resume.removeSkill(Skill.C);
        assertEquals(0, resume.getSkills().size());
    }

    @Test
    public void testRemoveSkillInvalid() {
        resume.removeSkill(Skill.CSS);
        assertEquals(1, resume.getSkills().size());
    }
    // cannot remove a "null" skill in UI or an empty skill, have to pick from list
}

