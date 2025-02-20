using Microsoft.AspNetCore.Diagnostics;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.AspNetCore.Mvc;
using System.Net;
using System.Text.Json;

namespace E_mart.CustomMiddlewares
{
    public class GlobalExceptionMiddleware : IMiddleware
    {
        public async Task InvokeAsync(HttpContext context, RequestDelegate next)
        {
            try
            {
                await next(context);
            }
            catch (CategoryNotFoundException ex)
            {
                await HandleExceptionAsync(context, ex, HttpStatusCode.NotFound);
            }
            catch (ProductNotFoundException ex)
            {
                await HandleExceptionAsync(context, ex, HttpStatusCode.NotFound);
            }
            //catch (ValidationException ex)
            //{
            //    await HandleExceptionAsync(context, ex, HttpStatusCode.BadRequest);
            //}
            catch (Exception ex)
            {
                await HandleExceptionAsync(context, ex, HttpStatusCode.InternalServerError);
            }
        }

        private static Task HandleExceptionAsync(HttpContext context, Exception exception, HttpStatusCode statusCode)
        {
            context.Response.ContentType = "application/json";
            context.Response.StatusCode = (int)statusCode;

            var response = new { message = exception.Message, statusCode = (int)statusCode };
            return context.Response.WriteAsync(JsonSerializer.Serialize(response));
        }

        
        public class CategoryNotFoundException : Exception
        {
            public CategoryNotFoundException(int categoryId) : base($"Category with ID {categoryId} not found.") { }
        }

        public class ProductNotFoundException : Exception
        {
            public ProductNotFoundException(int productId) : base($"Product with ID {productId} not found.") { }
        }

        public class ValidationException : Exception
        {
            public ValidationException(string message) : base(message) { }
        }
    }
}