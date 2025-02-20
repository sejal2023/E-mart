using E_mart.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace E_mart.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SubCategoryController : ControllerBase
    {
        private readonly ISubCategoryService _subCategoryService;

        public SubCategoryController(ISubCategoryService subcategoryService)
        {
            _subCategoryService = subcategoryService;
        }

        [HttpGet]
        public async Task<IActionResult> GetAllSubcategories()
        {
            var subcategories = await _subCategoryService.GetAllSubcategories(); 
            if (subcategories == null || !subcategories.Any()) 
            {
                return NotFound();
            }
            return Ok(subcategories);
        }

        [HttpGet("{subcategoryid}")]
        public async Task<IActionResult> GetSubcategoryById(int subcategoryid)
        {
            var subcategory = await _subCategoryService.GetSubcategoryById(subcategoryid); 
            if (subcategory == null)
            {
                return NotFound();
            }
            return Ok(subcategory);
        }

        [HttpGet("category/{categoryid}")]
        public async Task<IActionResult> GetSubcategoriesByCategory(int categoryid)
        {
            var subcategories = await _subCategoryService.GetSubcategoriesByCategory(categoryid); 
            if (subcategories == null || !subcategories.Any()) 
            {
                return NotFound();
            }
            return Ok(subcategories);
        }
    }
}
