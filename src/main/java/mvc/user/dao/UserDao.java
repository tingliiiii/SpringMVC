package mvc.user.dao;

import java.util.List;

import mvc.user.model.po.Statistics;
import mvc.user.model.po.User;

public interface UserDao {
	int addUser(User user);
	int updateUser(Integer userId, User user);
	int deleteUser(Integer userId);
	User getUserById(Integer userId);
	List<User> findAllUsers();
	Integer[] queryInterestsByUserId(Integer userId);
	
	List<Statistics> queryGenderStatistics();
	List<Statistics> queryEducationStatistics();
}
