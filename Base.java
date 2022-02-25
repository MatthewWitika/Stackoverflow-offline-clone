
import java.awt.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class Base {

    private static class Search {

        public Search() {
            
        }
    }
    
    public class User {

	int guestId;
	Search searchObj;
         

   
        
         ArrayList<Question> Questions;

	}

class Member extends User{

	Account account;
	ArrayList<Badge> badges;

	public void addQuestion(Question question)
        {
            
        }
	public void addComment(Entity entity, Comment comment){
            
        }
	public void addAnswer(Question question, Answer answer){
            
        }
	public void vote(Entity entity, VoteType voteType){
            
        }
	public void addTag(Question question, Tag tag){
            
        }
	public void flag(Entity entity){
            
        }
	public ArrayList<Badge> getBadges(){
            
        }

	}

class Account {
      int accountId;
      private String  Username;
      private String  Emailaddress;
      private String  Password;

     
    public String getUsername() {
        return Username;
    }

    public void setUsername(String name) {
        Username = name;
    }

    public String getEmailaddress() {
        return Emailaddress;
    }
    public void setEmailaddress(String email) {
        this.Emailaddress = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

	AccountStatus accountStatus;

	int reputation;
	}

class Moderator extends Member {

	public Boolean closeQuestion(Question question){
            
        }
	public Boolean restoreQuestion(Question question){
            
        }

	}

class Admin extends Member {

	public Boolean blockMember(Member member){
            
        }
	public Boolean unblockMember(Member member){
            
        }

	}

public enum AccountStatus {

	BLOCKED, ACTIVE, CLOSED;
	}

public enum VoteType {

	UPVOTE, DOWNVOTE, CLOSEVOTE, DELETEVOTE;
	}

public class Badge {

	String name;
	String description;	
	}

public class Entity {

	int entityId;
	Member creator;
	Map<VoteType, Integer> votes;
	Date createdDate;
	ArrayList<Comment> comments;

	public boolean flagEntity(Member member){
            
        }
	public boolean voteEntity(VoteType voteType){
            
        }
	public boolean addComment(Comment comment){
            
        }

	}

public class Comment extends Entity {

	String message;
	}

public class Question extends Entity {

	ArrayList<EditHistory> editHistoryList;
	ArrayList<Answer> answerList;
	ArrayList<Tag> tags;
	String title;
	String description;
	QuestionStatus status;

        
        public boolean addQuestion(){
            
        }
	public boolean addTag(Tag tag){
            
        }
	}

public class Answer extends Entity {

	String answer;
	Boolean isAccepted;
	public boolean addAnswer(Question question){
            
        }

	}

public enum QuestionStatus {

	ACTIVE, BOUNTIED, CLOSED, FLAGGED;
	}

public class Tag {

	String name;
	String description;

	}
public class EditHistory {

	int editHistoryId;
	Member creator;
	Date creationDate;
	Question prevQuestion;
	Question updatedQuestion;
	}


   

    
    
    
    
    
    }





    

