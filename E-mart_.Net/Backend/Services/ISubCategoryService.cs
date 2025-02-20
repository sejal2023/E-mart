using E_mart.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace E_mart.Services
{
    public interface ISubCategoryService
    {
        Task<List<Subcategory>> GetAllSubcategories(); 
        Task<Subcategory> GetSubcategoryById(int id);
        Task<List<Subcategory>> GetSubcategoriesByCategory(int categoryId); 
    }
}
