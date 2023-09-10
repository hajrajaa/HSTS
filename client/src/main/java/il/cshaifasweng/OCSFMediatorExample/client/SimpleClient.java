package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) throws IOException
	{
		Message message = (Message) msg;
		String messageSt = message.getMessage();
		System.out.println("message: "+messageSt);

		if (messageSt.equals("#LogInSuccessfully"))
		{
			User user = (User) message.getObject1();
			loginEvent newEvent= new loginEvent(user);
			Platform.runLater(()->{
			EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#loginWarning"))
		{
			Platform.runLater(()->{
			EventBus.getDefault().post(new WarningEvent((Warning) message.getObject1()));
					}
			);
		}
		else if (messageSt.equals("#successAlert"))
		{
			Platform.runLater(()->{
						EventBus.getDefault().post(new WarningSuccessEvent((String) message.getObject1()));
					}
			);
		}
		else if (messageSt.equals("#GetUserResponce"))
		{
			User user = (User) message.getObject1();
			getUserEvent newEvent = new getUserEvent(user);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if(messageSt.equals("#SolveExamResponse"))
		{

			Exam exam=(Exam) message.getObject1();
			SolveExamEvent newEvent=new SolveExamEvent(exam);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);

		}

		else if(messageSt.equals("#StartSolveSuccessfully"))
		{
			Object[] obj = (Object[]) message.getObject1();

			Exam exam=(Exam)obj[0];
			ExecutedExamInfo.ExamType examType=(ExecutedExamInfo.ExamType)obj[1];
			int examInfoID = (int)obj[2];
			int overtime = (int)obj[3];

			StartSolveExamEvent newEvent = new StartSolveExamEvent(exam,examType,examInfoID,overtime);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#StartSolveWarning"))
		{
			Platform.runLater(()->{
						EventBus.getDefault().post(new WarningEvent((Warning) message.getObject1()));
					}
			);
		}
		else if (messageSt.equals("#SolveExamWarning"))
		{
			Platform.runLater(()->{
						EventBus.getDefault().post(new WarningEvent((Warning) message.getObject1()));
					}
			);
		}

		else if (messageSt.equals("#ShowAllStudents"))
		{
			List<Student> students=(List<Student>) message.getObject1();
			for(int i=0;i<students.size();i++)
			{
				System.out.println(students.get(i).getUserName());
			}
			ShowStudentsEvent newEvent= new ShowStudentsEvent(students);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}

		else if (messageSt.equals("#ShowAllTeachers"))
		{
			System.out.println("SHSHSHSHSHSHSHSH");
			List<Teacher> teachers=(List<Teacher>) message.getObject1();
			for(int i=0;i<teachers.size();i++)
			{
				System.out.println(teachers.get(i).getUserName());
			}
			ShowTeachersEvent newEvent= new ShowTeachersEvent(teachers);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#ShowAllCourses"))
		{
			System.out.println("SHSHSHSHSHSHSHSH");
			List<Course> courses=(List<Course>) message.getObject1();
			for(int i=0;i<courses.size();i++)
			{
				System.out.println(courses.get(i).getCourseName());
			}
			ShowCoursesEvent newEvent= new ShowCoursesEvent(courses);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#ShowAllCoursesInfo"))
		{
			System.out.println("SHSHSHSHSHSHSHSH");
			List<ExecutedExamInfo> info=(List<ExecutedExamInfo>) message.getObject1();
			for(int i=0;i<info.size();i++)
			{
				System.out.println(info.get(i).getTitle());
			}
			ShowCoursesInfoEvent newEvent= new ShowCoursesInfoEvent(info);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#ShowStudentExecutedExams"))
		{
			ArrayList<ExecutedExam> exe_ex=(ArrayList<ExecutedExam>) message.getObject1();
			for(int i=0;i<exe_ex.size();i++)
			{
				System.out.println(exe_ex.get(i).getExamNum());
			}
			ShowStudentsExecutedExamsEvent newEvent= new ShowStudentsExecutedExamsEvent(exe_ex);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#ShowAllTeachersExamInfo"))
		{
			ArrayList<ExecutedExamInfo> exe_ex_info=(ArrayList<ExecutedExamInfo>) message.getObject1();
			for(int i=0;i<exe_ex_info.size();i++)
			{
				System.out.println(exe_ex_info.get(i).getCode());
			}
			ShowTeachersExamInfoEvent newEvent= new ShowTeachersExamInfoEvent(exe_ex_info);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#ShowTeachersExamInfoDetails"))
		{
			ExecutedExamInfo[] arrex_info=(ExecutedExamInfo[]) message.getObject1();
//
//			ExecutedExamInfo ex_info1 = arrex_info[0];
//			ExecutedExamInfo ex_info2 = arrex_info[1];
//
//			for(int i=0;i<exe_ex_info.size();i++)
//			{
//				System.out.println(exe_ex_info.get(i).getCode());
//			}
			ShowTeachersExamInfoDetailsEvent newEvent= new ShowTeachersExamInfoDetailsEvent(arrex_info);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#CodeForETExists"))
		{
			Exam exam = (Exam) message.getObject1();
			System.out.println(exam.getCodeExam());
			ShowExamEvent newEvent= new ShowExamEvent(exam);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#GetAllSubjectsResponce"))
		{
			List<Subject> allSubjects = (List<Subject>) message.getObject1();
			EventGetAllSubjects newEvent = new EventGetAllSubjects(allSubjects);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#getAllSubjectsNames_Replay"))
		{
			List<String> list = (List<String>) message.getObject1();
			EventGetAllSubjectsNames newEvent = new EventGetAllSubjectsNames(list);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if (messageSt.equals("#GetAllCoursesBySubject_Replay"))
		{
			List<String> list = (List<String>) message.getObject1();
			EventGetAllCoursesBySubject newEvent = new EventGetAllCoursesBySubject(list);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if (messageSt.equals("#GetAllExamsByCourse_Replay"))
		{
			List<Exam> list = (List<Exam>) message.getObject1();
			EventGetAllExamsByCourse newEvent = new EventGetAllExamsByCourse(list);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
    	else if (messageSt.equals("#GetAllQuestionsByCourse_Replay"))
		{
			List<Question> list = (List<Question>) message.getObject1();
			EventGetAllQuestionsByCourse newEvent = new EventGetAllQuestionsByCourse(list);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if (messageSt.equals("#GetAllWrittenExamRes"))
		{
			List<ExecutedExamInfo> allwrittenInfo = (List<ExecutedExamInfo>) message.getObject1();
			WrittenExamViewEvent newevent= new WrittenExamViewEvent(allwrittenInfo);
			Platform.runLater(()->{
						EventBus.getDefault().post(newevent);
					}
			);
		}
		else if (messageSt.equals("#GetAllExcutedExamRes"))
		{
			List<ExecutedExamInfo> allexcutedInfo = (List<ExecutedExamInfo>) message.getObject1();
			ExcutedExamViewEvent newevent= new ExcutedExamViewEvent(allexcutedInfo);
			Platform.runLater(()->{
						EventBus.getDefault().post(newevent);
					}
			);
		}
		else if(messageSt.equals("#GetExcutedExamRes"))
		{
			List<ExecutedExam> allExcutedExams = (List<ExecutedExam>) message.getObject1();

			ExcutedExamEvent  newEvent=new ExcutedExamEvent(allExcutedExams);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if(messageSt.equals("#GeWrittenExamRes"))
		{
			List<ExecutedExam> allWrittenExams = (List<ExecutedExam>) message.getObject1();

			WrittenExamEvent  newEvent=new WrittenExamEvent(allWrittenExams);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);

		}
		else if (messageSt.equals("#UpdateGradeWarning"))
		{
			Platform.runLater(()->{
						EventBus.getDefault().post(new WarningEvent((Warning) message.getObject1()));
					}
			);
		}
		else if(messageSt.equals("#ApproveGradeWarning"))
		{
			Platform.runLater(()->{
						EventBus.getDefault().post(new WarningEvent((Warning) message.getObject1()));
					}
			);
		}
		else if(messageSt.equals(("#GradeApprovedSuccessfully")))
		{
			ExecutedExam executedExam=(ExecutedExam) message.getObject1();
			ApproveGradeEvent newEvent =new ApproveGradeEvent(executedExam);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#UpdateGradeSuccessfully"))
		{
			ExecutedExam executedExam=(ExecutedExam) message.getObject1();
			UpdateGradeEvent newEvent =new UpdateGradeEvent(executedExam);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if (messageSt.equals("#GetTeacherAllExams_Replay"))
		{
			Object [] data = (Object[]) message.getObject1();
			ArrayList<ExecutedExamInfo> writtenExamsInfoList = (ArrayList<ExecutedExamInfo>) data[0];
			ArrayList<ExecutedExamInfo> executedExamInfoList = (ArrayList<ExecutedExamInfo>) data[1];

			EventGetTeacherAllExams newEvent= new EventGetTeacherAllExams(writtenExamsInfoList,executedExamInfoList);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if(messageSt.equals(("#drawExamRes")))
		{
			Platform.runLater(()->{
						EventBus.getDefault().post(new WarningEvent((Warning) message.getObject1()));
					}
			);
		}
		else if(messageSt.equals(("#getExamCopy_Replay")))
		{
			ExecutedVirtual vExam = (ExecutedVirtual) message.getObject1();
			EventGetExamCopy newEvent = new EventGetExamCopy(vExam);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if(messageSt.equals(("#GetAllOvertimeRequests_Replay")))
		{
			Object [] data = (Object[]) message.getObject1();
			ArrayList<OvertimeRequest> list = (ArrayList<OvertimeRequest>) data[0];
			boolean switchPage = (boolean) data[1];
			EventGetAllOvertimeRequests newEvent = new EventGetAllOvertimeRequests(list, switchPage);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}

		else if (messageSt.equals("#GetStudentExecutedExams_Replay"))
		{
			ArrayList<ExecutedVirtual> executedVirtual=(ArrayList<ExecutedVirtual>) message.getObject1();
			GetStudentExamsEvent newEvent=new GetStudentExamsEvent(executedVirtual);
			Platform.runLater(()->{
						EventBus.getDefault().post(newEvent);
					}
			);
		}
		else if(messageSt.equals("#GetRefreshExcutedExamsRes"))
		{
      Object [] data = (Object[]) message.getObject1();
      ExecutedExamInfo newExamInfo= (ExecutedExamInfo)data[0];
      ArrayList<ExecutedExam>  allExcutedExams = (ArrayList<ExecutedExam>) data[1];
      refreshExecExam  newEvent=new refreshExecExam(allExcutedExams,newExamInfo);
      Platform.runLater(()->{
            EventBus.getDefault().post(newEvent);
          }
      );
	  }
		else if(messageSt.equals("#PrincipleStatisticsLists_Replay"))
		{
			Object [] data = (Object[]) message.getObject1();
			ArrayList<StatisticsFilter> studentsList = (ArrayList<StatisticsFilter>) data[0];
			ArrayList<StatisticsFilter> teachersList = (ArrayList<StatisticsFilter>) data[1];
			ArrayList<StatisticsFilter> coursesList = (ArrayList<StatisticsFilter>) data[2];

			EventPrincipleStatisticsLists newEvent = new EventPrincipleStatisticsLists(studentsList,teachersList,coursesList);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if(messageSt.equals("#GetAllExamsInfoByFilter_Replay"))
		{
			ArrayList<StatisticsInfo> list = (ArrayList<StatisticsInfo>) message.getObject1();

			EventAllStatisticsInfo newEvent = new EventAllStatisticsInfo(list);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}
		else if(messageSt.equals("#ApproveOvertimeRequest_Replay"))
		{
			Object [] data = (Object[]) message.getObject1();

			int id = (int) data[0];
			int time = (int) data[1];
			EventOvertimeAdded newEvent = new EventOvertimeAdded(id, time);
			Platform.runLater(()->{EventBus.getDefault().post(newEvent);});
		}

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("172.20.10.4", 3000);
//			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
