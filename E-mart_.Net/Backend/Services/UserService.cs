using E_mart.Models;
using E_mart.Repositories;
using Microsoft.EntityFrameworkCore;
using Org.BouncyCastle.Crypto.Generators;

namespace E_mart.Services
{
    public class UserService : IUserService
    {
        private readonly eMartDbContext _dbContext;
        public UserService(eMartDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<User> CreateUserAsync(User user)
        {
            user.PasswordHash = BCrypt.Net.BCrypt.HashPassword(user.PasswordHash);
            _dbContext.Set<User>().Add(user);
            await _dbContext.SaveChangesAsync();
            return user;

        }

        public async Task<User> FindUserByEmailAsync(string email)
        {
            var user = await _dbContext.Set<User>().FirstOrDefaultAsync(u => u.Email == email);
            if (user == null)
            {
                return null;
            }
            return user;
        }

        public async Task<List<User>> GetAllUsersAsync()
        {
            return await _dbContext.Set<User>().ToListAsync();
        }

        public async Task<User> ValidateUserCredentialsAsync(string email, string password)
        {
            var user = await FindUserByEmailAsync(email);
            if (!BCrypt.Net.BCrypt.Verify(password, user.PasswordHash))
            {
                return null;
            }
            return user;
        }
    }
}

