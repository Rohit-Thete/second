package museum;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.Scanner;

import entity.Category;
import exception.ResourceAlreadyExistException;
import exception.ResourseNotFoundException;
import service.ArticleService;

public class ArticleMain {
	
	public static void main(String[] args) {
		
		boolean exit = true;
		Scanner sc = new Scanner(System.in);
		ArticleService service = null;
		try {
			 service = new ArticleService();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		while(exit)
		{
			System.out.println("++++ Menu ++++"
					+"\n1.Add Article"
					+"\n2.Display All Articles"
					+"\n3.Diaplay Details of Articles"
					+"\n0.Exit");
			int ch = sc.nextInt();
			switch(ch)
			{
			case 1:{
				try {
					System.out.println("Enter article name,category(PAINTING,SCULPTURE,ARTIFACT),created Date, creator name : ");
					String name = sc.next();
					Category category = Category.valueOf(sc.next());
					LocalDate date = LocalDate.parse(sc.next());
					String creatorName = sc.next();
					service.addArticle(name, category, date, creatorName);
				} catch (SQLException | ResourceAlreadyExistException e) {
					System.err.println(e.getMessage());
				}
				break;
			}
			case 2:{
				try {
					service.displayAllArticles();
				} catch ( SQLException e) {
					System.err.println(e.getMessage());
				}
				break;
				
			}
			case 3:{
				try {
					System.out.println("Enter Article id : ");
					service.displayArticleDetails(sc.nextInt());
				} catch (SQLException | ResourseNotFoundException e) {
					System.err.println(e.getMessage());
				}
				
			}
			
			case 4: {
				System.out.println("enter id");
				try {
					service.deleteArticle(sc.nextInt());
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			}
			case 5: {
				System.out.println("enter id, name, category(PAINTING,SCULPTURE, ARTIFACT),date, creator name");
				try {
					service.updateArticle(sc.nextInt(), sc.next(), Category.valueOf(sc.next()), LocalDate.parse(sc.next()), sc.next());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			

			case 0:{
				exit = false;
				break;
			}
			default:
				System.err.println("Enter valid choice");
			}
		}
		

			
		}

	}


