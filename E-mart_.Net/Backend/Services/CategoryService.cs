using E_mart.Models;
using E_mart.Repositories;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Services
{
    public class CategoryService : ICategoryService
    {
        private readonly eMartDbContext _context;

        public CategoryService(eMartDbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Category>> GetAllCategories()
        {
            return await _context.Categories.ToListAsync();
        }

        public async Task<Category?> GetCategoryById(int id)
        {
            return await _context.Categories.FindAsync(id);
        }


    }
}
