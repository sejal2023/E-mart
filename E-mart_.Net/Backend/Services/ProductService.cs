using E_mart.Models;
using E_mart.Repositories;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace E_mart.Services
{
    public class ProductService : IProductService
    {
        private readonly eMartDbContext _context;

        public ProductService(eMartDbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Product>> GetAllProducts()
        {
            return await _context.Products.ToListAsync();
        }

        public async Task<Product> GetProductById(int id)
        {
            return await _context.Products.FindAsync(id);
            //return await _context.Products.FirstOrDefaultAsync(p => p.Productid == id);
        }

        public async Task<IEnumerable<Product>> GetProductsByCategory(int categoryid)
        {
            return await _context.Products
                                 .Where(p => p.Categoryid == categoryid)
                                 .ToListAsync();
        }

        public async Task<IEnumerable<Product>> GetProductsBySubcategory(int subcategoryid)
        {
            return await _context.Products
                                 .Where(p => p.Subcategoryid == subcategoryid)
                                 .ToListAsync();
        }
    }
}
