using E_mart.Models;

namespace E_mart.Services
{
    public interface IUserService
    {
        Task<User> CreateUserAsync(User user);
        Task<List<User>> GetAllUsersAsync();
        Task<User> FindUserByEmailAsync(string email);
        Task<User> ValidateUserCredentialsAsync(string email, string password);
        // Task<User> UpdateUserAsync(int userId, User userDetails);
        // Task DeleteUserAsync(int userId);
    }
}

