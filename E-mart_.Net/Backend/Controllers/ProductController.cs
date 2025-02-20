using E_mart.Models;
using E_mart.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using static E_mart.CustomMiddlewares.GlobalExceptionMiddleware;

namespace E_mart.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    //[Authorize]
    public class ProductController : ControllerBase
    {
        private readonly IProductService _productService;

        public ProductController(IProductService productService)
        {
            _productService = productService;
        }

        [HttpGet]
        public async Task<IActionResult> GetAllProducts()
        {
            var products = await _productService.GetAllProducts();
            return Ok(products);
        }

        [HttpGet("{productid}")]
        public async Task<IActionResult> GetProductById(int productid)
        {
            var product = await _productService.GetProductById(productid);
            if(product == null)
            {
                throw new ProductNotFoundException(productid);
            }
            return Ok(product);
        }

        [HttpGet("categories/{categoryid}")]
        public async Task<IActionResult> GetProductsByCategory(int categoryid)
        {
            var product = await _productService.GetProductsByCategory(categoryid);
            if (product == null)
            {
                return NotFound();
            }
            return Ok(product);

        }


        [HttpGet("subcategories/{subcategoryid}")]
        public async Task<IActionResult> GetProductsBySubCategory(int subcategoryid)
        {
            var product = await _productService.GetProductsBySubcategory(subcategoryid);
            if (product == null)
            {
                return NotFound();
            }
            return Ok(product);
        }
        





    }
}
