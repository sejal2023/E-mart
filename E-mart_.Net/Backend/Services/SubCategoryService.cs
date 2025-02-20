using E_mart.Models;
using E_mart.Repositories;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace E_mart.Services
{
    public class SubCategoryService : ISubCategoryService
    {
        private readonly eMartDbContext _context;

        public SubCategoryService(eMartDbContext context)
        {
            _context = context;
        }

        public async Task<List<Subcategory>> GetAllSubcategories()
        {
            return await _context.Subcategories.ToListAsync();
        }

        public async Task<List<Subcategory>> GetSubcategoriesByCategory(int categoryId)
        {
            return await _context.Subcategories
                .Where(s => s.Categoryid == categoryId)
                .ToListAsync();
        }

        public async Task<Subcategory> GetSubcategoryById(int id)
        {
            return await _context.Subcategories.FindAsync(id);
        }
    }
}
