package in.afckstechnologies.afckssalesapp.jsonparser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.afckstechnologies.afckssalesapp.models.AdminStudentsDAO;
import in.afckstechnologies.afckssalesapp.models.BatchesForStudentsDAO;
import in.afckstechnologies.afckssalesapp.models.CampaignStudentsDAO;
import in.afckstechnologies.afckssalesapp.models.CenterDAO;
import in.afckstechnologies.afckssalesapp.models.CommentModeDAO;
import in.afckstechnologies.afckssalesapp.models.ContactCallingListDAO;
import in.afckstechnologies.afckssalesapp.models.ContactEnquiriesListDAO;
import in.afckstechnologies.afckssalesapp.models.DayPrefrenceDAO;
import in.afckstechnologies.afckssalesapp.models.NewCoursesDAO;
import in.afckstechnologies.afckssalesapp.models.StCenterDAO;
import in.afckstechnologies.afckssalesapp.models.StCoursesDAO;
import in.afckstechnologies.afckssalesapp.models.StudentsDAO;
import in.afckstechnologies.afckssalesapp.models.StudentsFeedbackListDAO;
import in.afckstechnologies.afckssalesapp.models.StudentsInBatchListDAO;
import in.afckstechnologies.afckssalesapp.models.TemplatesContactDAO;
import in.afckstechnologies.afckssalesapp.models.UserFeedbacksDAO;
import in.afckstechnologies.afckssalesapp.view.StudentsAttendanceDetailsDAO;


public class JsonHelper {


    private ArrayList<TemplatesContactDAO> templatesContactDAOArrayList = new ArrayList<TemplatesContactDAO>();
    private TemplatesContactDAO templatesContactDAO;

    private ArrayList<CenterDAO> centerDAOArrayList = new ArrayList<CenterDAO>();
    private CenterDAO centerDAO;

    private ArrayList<NewCoursesDAO> newCoursesDAOArrayList = new ArrayList<NewCoursesDAO>();
    private NewCoursesDAO newCoursesDAO;

    private ArrayList<StudentsAttendanceDetailsDAO> studentsAttendanceDetailsDAOArrayList = new ArrayList<StudentsAttendanceDetailsDAO>();
    private StudentsAttendanceDetailsDAO studentsAttendanceDetailsDAO;

    private ArrayList<AdminStudentsDAO> adminStudentsDAOArrayList = new ArrayList<AdminStudentsDAO>();
    private AdminStudentsDAO adminStudentsDAO;
    private ArrayList<DayPrefrenceDAO> DayPrefrenceDAOArrayList = new ArrayList<DayPrefrenceDAO>();
    private DayPrefrenceDAO dayPrefrenceDAO;
    private ArrayList<StCoursesDAO> stCoursesDAOArrayList = new ArrayList<StCoursesDAO>();
    private StCoursesDAO stCoursesDAO;
    private ArrayList<StCenterDAO> stCenterDAOArrayList = new ArrayList<StCenterDAO>();
    private StCenterDAO stCenterDAO;

    private ArrayList<StudentsInBatchListDAO> studentsInBatchListDAOs = new ArrayList<StudentsInBatchListDAO>();
    private StudentsInBatchListDAO studentsInBatchListDAO;


    private ArrayList<BatchesForStudentsDAO> batchDAOArrayList = new ArrayList<BatchesForStudentsDAO>();
    private BatchesForStudentsDAO batchDAO;


    private ArrayList<CommentModeDAO> commentModeDAOArrayList = new ArrayList<CommentModeDAO>();
    private CommentModeDAO commentModeDAO;

    private ArrayList<UserFeedbacksDAO> userFeedbacksDAOArrayList = new ArrayList<UserFeedbacksDAO>();
    private UserFeedbacksDAO userFeedbacksDAO;

    private ArrayList<CampaignStudentsDAO> campaignStudentsDAOArrayList = new ArrayList<CampaignStudentsDAO>();
    private CampaignStudentsDAO campaignStudentsDAO;

    private ArrayList<ContactCallingListDAO> contactCallingListDAOs = new ArrayList<ContactCallingListDAO>();
    private ContactCallingListDAO contactListDAO;

    private ArrayList<StudentsDAO> studentsDAOArrayList = new ArrayList<StudentsDAO>();
    private StudentsDAO studentsDAO;

    private ArrayList<StudentsFeedbackListDAO> studentsFeedbackListDAOs = new ArrayList<StudentsFeedbackListDAO>();
    private StudentsFeedbackListDAO studentsFeedbackListDAO;

    private ArrayList<ContactEnquiriesListDAO> contactEnquiriesListDAOArrayList = new ArrayList<ContactEnquiriesListDAO>();
    private ContactEnquiriesListDAO enquiriesListDAO;
    //studentPaser
    public ArrayList<AdminStudentsDAO> parseAdminStudentList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                adminStudentsDAO = new AdminStudentsDAO();
                String sequence = String.format("%03d", i + 1);
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                adminStudentsDAO.setBranch_id(json_data.getString("branch_id"));
                adminStudentsDAO.setCourse_id(json_data.getString("course_id"));
                adminStudentsDAO.setEmail_id(json_data.getString("email_id"));
                adminStudentsDAO.setGender(json_data.getString("gender"));
                adminStudentsDAO.setStudent_Name(json_data.getString("Student_Name"));
                adminStudentsDAO.setUser_id(json_data.getString("user_id"));
                adminStudentsDAO.setMobile_no(json_data.getString("mobile_no"));
                adminStudentsDAO.setFirst_name(json_data.getString("first_name"));
                adminStudentsDAO.setSourse(json_data.getString("Source"));
                adminStudentsDAO.setNotes(json_data.getString("Notes"));
                adminStudentsDAO.setJob_program_status(json_data.getString("job_program_status"));
                adminStudentsDAO.setDemo(json_data.getString("demo"));
                adminStudentsDAO.setSt_batch_status(json_data.getString("st_batch_status"));
                adminStudentsDAO.setNumbers("" + sequence);
                adminStudentsDAOArrayList.add(adminStudentsDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return adminStudentsDAOArrayList;
    }


    //templatePaser
    public ArrayList<TemplatesContactDAO> parseTemplateList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                templatesContactDAO = new TemplatesContactDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                templatesContactDAO.setID(json_data.getString("ID"));
                templatesContactDAO.setSubject(json_data.getString("Subject"));
                templatesContactDAO.setTag(json_data.getString("tag"));
                templatesContactDAO.setTemplate_Text(json_data.getString("Template_Text"));
                templatesContactDAOArrayList.add(templatesContactDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return templatesContactDAOArrayList;
    }


    //studentlistattendancedetailsrPaser
    public ArrayList<StudentsAttendanceDetailsDAO> parseStudentAttendanceDetailsList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);
            int len = leadJsonObj.length();
            for (int i = 0; i < leadJsonObj.length(); i++) {
                String sequence = String.format("%03d", len--);
                studentsAttendanceDetailsDAO = new StudentsAttendanceDetailsDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                studentsAttendanceDetailsDAO.setBatch_id(json_data.getString("batch_id"));
                studentsAttendanceDetailsDAO.setStudent_name(json_data.getString("student_name"));
                studentsAttendanceDetailsDAO.setAttendance(json_data.getString("attendance"));
                studentsAttendanceDetailsDAO.setAttendanceDate(json_data.getString("AttendanceDate"));
                studentsAttendanceDetailsDAO.setNumbers("" + sequence);
                studentsAttendanceDetailsDAOArrayList.add(studentsAttendanceDetailsDAO);

            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return studentsAttendanceDetailsDAOArrayList;
    }


    //centerPaser
    public ArrayList<CenterDAO> parseCenterList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                centerDAO = new CenterDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                centerDAO.setId(json_data.getString("id"));
                centerDAO.setBranch_name(json_data.getString("branch_name"));
                centerDAO.setAddress(json_data.getString("address"));
                centerDAO.setIsselected(json_data.getString("isselected"));
                centerDAOArrayList.add(centerDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return centerDAOArrayList;
    }

    //newcoursesPaser
    public ArrayList<NewCoursesDAO> parseNewCoursesList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                newCoursesDAO = new NewCoursesDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                newCoursesDAO.setId(json_data.getString("id"));
                newCoursesDAO.setCourse_type_id(json_data.getString("course_type_id"));
                newCoursesDAO.setCourse_code(json_data.getString("course_code"));
                newCoursesDAO.setCourse_name(json_data.getString("course_name"));
                newCoursesDAO.setTime_duration(json_data.getString("time_duration"));
                newCoursesDAO.setPrerequisite(json_data.getString("prerequisite"));
                newCoursesDAO.setRecommonded(json_data.getString("recommonded"));
                newCoursesDAO.setFees(json_data.getString("fees"));
                newCoursesDAO.setIsselected(json_data.getString("isselected"));
                newCoursesDAOArrayList.add(newCoursesDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newCoursesDAOArrayList;
    }


    //centerPaser
    public ArrayList<DayPrefrenceDAO> parseDayPrefrenceList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                dayPrefrenceDAO = new DayPrefrenceDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                dayPrefrenceDAO.setId(json_data.getString("id"));
                dayPrefrenceDAO.setPrefrence(json_data.getString("Prefrence"));
                dayPrefrenceDAO.setIsselected(json_data.getString("isselected"));
                DayPrefrenceDAOArrayList.add(dayPrefrenceDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return DayPrefrenceDAOArrayList;
    }

    //stcoursesPaser
    public ArrayList<StCoursesDAO> parseStCoursesList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                stCoursesDAO = new StCoursesDAO();
                String sequence = String.format("%03d", i + 1);
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                stCoursesDAO.setId(json_data.getString("id"));
                stCoursesDAO.setType_name(json_data.getString("type_name"));
                stCoursesDAO.setCourse_code(json_data.getString("course_code"));
                stCoursesDAO.setCourse_name(json_data.getString("course_name"));
                stCoursesDAO.setType_name_id(json_data.getString("type_name_id"));
                stCoursesDAO.setNumbers("" + sequence);
                stCoursesDAOArrayList.add(stCoursesDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stCoursesDAOArrayList;
    }

    //stcenterPaser
    public ArrayList<StCenterDAO> parseStCenterList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                stCenterDAO = new StCenterDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                stCenterDAO.setId(json_data.getString("id"));
                stCenterDAO.setBranch_name(json_data.getString("branch_name"));
                stCenterDAOArrayList.add(stCenterDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stCenterDAOArrayList;
    }

    //batchstudentPaser
    public ArrayList<StudentsInBatchListDAO> parseBatchStudentList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                studentsInBatchListDAO = new StudentsInBatchListDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                studentsInBatchListDAO.setEmail_id(json_data.getString("email_id"));
                studentsInBatchListDAO.setGender(json_data.getString("gender"));
                studentsInBatchListDAO.setStudent_Name(json_data.getString("Students_Name"));
                studentsInBatchListDAO.setMobile_no(json_data.getString("mobile_no"));
                studentsInBatchListDAO.setFirst_name(json_data.getString("first_name"));
                studentsInBatchListDAO.setBaseFees(json_data.getString("BaseFees"));
                studentsInBatchListDAO.setCourse_name(json_data.getString("course_name"));
                studentsInBatchListDAO.setFees(json_data.getString("fees"));
                studentsInBatchListDAO.setStart_date(json_data.getString("start_date"));
                studentsInBatchListDAO.setBatch_code(json_data.getString("batch_code"));
                studentsInBatchListDAO.setStatus(json_data.getString("Status"));
                studentsInBatchListDAO.setPrevious_attendance(json_data.getString("previous_attendance"));
                studentsInBatchListDAO.setDiscontinue_reason(json_data.getString("discontinue_reason"));
                studentsInBatchListDAOs.add(studentsInBatchListDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return studentsInBatchListDAOs;
    }


    //newcoursesPaser
    public ArrayList<BatchesForStudentsDAO> parseBatchesList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                batchDAO = new BatchesForStudentsDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                batchDAO.setBatchtype(json_data.getString("batchtype"));
                batchDAO.setId(json_data.getString("id"));
                batchDAO.setStart_date(json_data.getString("new_start_date"));
                batchDAO.setTimings(json_data.getString("timings"));
                batchDAO.setFrequency(json_data.getString("frequency"));
                batchDAO.setDuration(json_data.getString("duration"));
                batchDAO.setFees(json_data.getString("fees"));
                batchDAO.setFaculty_Name(json_data.getString("faculty_Name"));
                batchDAO.setNotes(json_data.getString("Notes"));
                batchDAO.setCourse_name(json_data.getString("course_name"));
                batchDAO.setBranch_name(json_data.getString("branch_name"));
                batchDAOArrayList.add(batchDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return batchDAOArrayList;
    }


    //studentlistfeesdetailsrPaser
    public ArrayList<CommentModeDAO> parseStudentCommentDetailsList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                commentModeDAO = new CommentModeDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                commentModeDAO.setId(json_data.getString("id"));
                commentModeDAO.setStudent_comments(json_data.getString("student_comment"));
                commentModeDAO.setDate_comments(json_data.getString("display_date"));
                commentModeDAOArrayList.add(commentModeDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return commentModeDAOArrayList;
    }
//userfeedback approval Paser
    public ArrayList<UserFeedbacksDAO> parseUserFeedbacksList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                userFeedbacksDAO = new UserFeedbacksDAO();
                String sequence = String.format("%03d", i + 1);
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                userFeedbacksDAO.setId(json_data.getString("id"));
                userFeedbacksDAO.setHeader(json_data.getString("Header"));
                userFeedbacksDAO.setEmail_id(json_data.getString("email_id"));
                userFeedbacksDAO.setFeedback(json_data.getString("feedback"));
                userFeedbacksDAO.setStudent_name(json_data.getString("student_name"));
                userFeedbacksDAO.setUser_id(json_data.getString("user_id"));
                userFeedbacksDAO.setMobile_no(json_data.getString("mobile_no"));
                userFeedbacksDAO.setFeedbackDate(json_data.getString("FeedbackDate"));
                userFeedbacksDAO.setFooter(json_data.getString("Footer"));
                userFeedbacksDAO.setNumbers("" + sequence);
                userFeedbacksDAOArrayList.add(userFeedbacksDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userFeedbacksDAOArrayList;
    }

    public ArrayList<CampaignStudentsDAO> parseCampaignStudentList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                campaignStudentsDAO = new CampaignStudentsDAO();
                String sequence = String.format("%03d", i + 1);
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                campaignStudentsDAO.setCampaign_id(json_data.getString("campaign_id"));
                campaignStudentsDAO.setEmail_id(json_data.getString("email_id"));
                campaignStudentsDAO.setGender(json_data.getString("gender"));
                campaignStudentsDAO.setStudent_Name(json_data.getString("Student_Name"));
                campaignStudentsDAO.setUser_id(json_data.getString("user_id"));
                campaignStudentsDAO.setMobile_no(json_data.getString("mobile_no"));
                campaignStudentsDAO.setFirst_name(json_data.getString("first_name"));
                campaignStudentsDAO.setSourse(json_data.getString("Source"));
                campaignStudentsDAO.setNotes(json_data.getString("Notes"));
                campaignStudentsDAO.setStarred(json_data.getString("starred"));
                campaignStudentsDAO.setNumbers("" + sequence);
                campaignStudentsDAOArrayList.add(campaignStudentsDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return campaignStudentsDAOArrayList;
    }

    //callbackstudentPaser
    public ArrayList<ContactCallingListDAO> parsecallUserList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                contactListDAO = new ContactCallingListDAO();
                String sequence = String.format("%03d", i + 1);
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                contactListDAO.setId(json_data.getString("id"));
                contactListDAO.setMobile_no(json_data.getString("mobile_no"));
                contactListDAO.setDate(json_data.getString("created_at"));
                contactListDAO.setStatus(json_data.getString("status"));
                contactListDAO.setEmail_id(json_data.getString("email_id"));
                contactListDAO.setFirst_name(json_data.getString("first_name"));
                contactListDAO.setLast_name(json_data.getString("last_name"));
                contactListDAO.setStart_date(json_data.getString("start_date"));
                contactListDAO.setEnd_date(json_data.getString("end_date"));
                contactListDAO.setNotes(json_data.getString("notes"));
                contactCallingListDAOs.add(contactListDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return contactCallingListDAOs;
    }

    //studentPaser
    public ArrayList<StudentsDAO> parseShowStudentList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                studentsDAO = new StudentsDAO();
                String sequence = String.format("%03d", i + 1);
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                studentsDAO.setBranch_id(json_data.getString("branch_id"));
                studentsDAO.setCourse_id(json_data.getString("course_id"));
                studentsDAO.setEmail_id(json_data.getString("email_id"));
                studentsDAO.setGender(json_data.getString("gender"));
                studentsDAO.setStudents_Name(json_data.getString("Student_Name"));
                studentsDAO.setUser_id(json_data.getString("user_id"));
                studentsDAO.setMobile_no(json_data.getString("mobile_no"));
                studentsDAO.setFirst_name(json_data.getString("first_name"));
                studentsDAO.setSourse(json_data.getString("Source"));
                studentsDAO.setNotes(json_data.getString("Notes"));
                studentsDAO.setNumbers("" + sequence);
                studentsDAOArrayList.add(studentsDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return studentsDAOArrayList;
    }
    //studentfeedbacklistfeesdetailsrPaser
    public ArrayList<StudentsFeedbackListDAO> parseStudentFeedbackDetailsList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                String sequence = String.format("%03d", i + 1);
                studentsFeedbackListDAO = new StudentsFeedbackListDAO();
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                studentsFeedbackListDAO.setId(json_data.getString("id"));
                studentsFeedbackListDAO.setBatchID(json_data.getString("BatchID"));
                studentsFeedbackListDAO.setFirst_name(json_data.getString("first_name"));
                studentsFeedbackListDAO.setLast_name(json_data.getString("last_name"));
                studentsFeedbackListDAO.setMobile_no(json_data.getString("mobile_no"));
                studentsFeedbackListDAO.setFeedback(json_data.getString("Feedback"));
                studentsFeedbackListDAO.setFeedback_date(json_data.getString("feedback_date"));
                studentsFeedbackListDAO.setEmail_id(json_data.getString("email_id"));
                studentsFeedbackListDAO.setQ1(json_data.getString("Q1"));
                studentsFeedbackListDAO.setQ2(json_data.getString("Q2"));
                studentsFeedbackListDAO.setQ3(json_data.getString("Q3"));
                studentsFeedbackListDAO.setNumbers("" + sequence);
                studentsFeedbackListDAOs.add(studentsFeedbackListDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return studentsFeedbackListDAOs;
    }

    //callbackstudentPaser
    public ArrayList<ContactEnquiriesListDAO> parseEnquiriesUserList(String rawLeadListResponse) {
        // TODO Auto-generated method stub
        Log.d("scheduleListResponse", rawLeadListResponse);
        try {
            JSONArray leadJsonObj = new JSONArray(rawLeadListResponse);

            for (int i = 0; i < leadJsonObj.length(); i++) {
                enquiriesListDAO = new ContactEnquiriesListDAO();
                String sequence = String.format("%03d", i + 1);
                JSONObject json_data = leadJsonObj.getJSONObject(i);
                enquiriesListDAO.setId(json_data.getString("id"));
                enquiriesListDAO.setMobile_number(json_data.getString("mobile_number"));
                enquiriesListDAO.setCaller_comments(json_data.getString("caller_comments"));
                enquiriesListDAO.setRequirement(json_data.getString("requirement"));
                enquiriesListDAO.setLooking_for(json_data.getString("looking_for"));
                enquiriesListDAO.setStarred(json_data.getString("starred"));
                enquiriesListDAO.setFull_name(json_data.getString("full_name"));
                enquiriesListDAO.setDate_of_enquiry(json_data.getString("date_of_enquiry"));
                enquiriesListDAO.setCall_status(json_data.getString("call_status"));

                contactEnquiriesListDAOArrayList.add(enquiriesListDAO);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return contactEnquiriesListDAOArrayList;
    }


}
