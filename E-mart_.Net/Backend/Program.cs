//using E_mart.Repositories;
//using E_mart.Services;
//using Microsoft.AspNetCore.Builder;
//using Microsoft.AspNetCore.Hosting;
//using Microsoft.AspNetCore.Http;
//using Microsoft.AspNetCore.Mvc;
//using Microsoft.EntityFrameworkCore;
//using Microsoft.Extensions.Configuration;
//using Microsoft.Extensions.DependencyInjection;
//using Microsoft.Extensions.Hosting;

//namespace E_mart
//{
//    public class Program
//    {
//        public static void Main(string[] args)
//        {
//            var builder = WebApplication.CreateBuilder(args);

//            // Add services to the container.

//            builder.Services.AddControllers().AddJsonOptions(options =>
//            {
//                options.JsonSerializerOptions.ReferenceHandler = System.Text.Json.Serialization.ReferenceHandler.IgnoreCycles;
//            });

//            builder.Services.AddControllers();
//            // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle

//            //builder.Services.AddDbContext<eMartDbContext>(options =>
//            //    options.UseMySql(builder.Configuration.GetConnectionString("emartConnection")));


//            builder.Services.AddDbContext<eMartDbContext>(options =>
//                    options.UseMySql(builder.Configuration.GetConnectionString("emartConnection"),
//                    ServerVersion.Parse("8.0.39-mysql")));

//            //builder.Services.AddScoped<ICategoryRepository, CategoryRepository>();
//            builder.Services.AddScoped<ICategoryService, CategoryService>();
//            builder.Services.AddScoped<IProductService, ProductService>();
//            builder.Services.AddScoped<ISubCategoryService, SubCategoryService>();
//            builder.Services.AddScoped<IOrderService, OrderService>();
//            builder.Services.AddScoped<ICartService, CartService>();

//            // Enable CORS
//            builder.Services.AddCors(options =>
//            {
//                options.AddPolicy("AllowSpecificOrigin",
//                    policy => policy.WithOrigins("http://localhost:5173")
//                                    .AllowAnyHeader()
//                                    .AllowAnyMethod());
//            });


//            builder.Services.AddEndpointsApiExplorer();
//            builder.Services.AddSwaggerGen();

//            var app = builder.Build();

//            // Configure the HTTP request pipeline.
//            if (app.Environment.IsDevelopment())
//            {
//                app.UseSwagger();
//                app.UseSwaggerUI();
//            }

//            app.UseHttpsRedirection();

//            app.UseAuthorization();


//            app.MapControllers();

//            app.Run();
//        }
//    }
//}



//using E_mart.Repositories;
//using E_mart.Services;
//using Microsoft.AspNetCore.Builder;
//using Microsoft.AspNetCore.Hosting;
//using Microsoft.AspNetCore.Http;
//using Microsoft.AspNetCore.Mvc;
//using Microsoft.EntityFrameworkCore;
//using Microsoft.Extensions.Configuration;
//using Microsoft.Extensions.DependencyInjection;
//using Microsoft.Extensions.Hosting;

//namespace E_mart
//{
//    public class Program
//    {
//        public static void Main(string[] args)
//        {
//            var builder = WebApplication.CreateBuilder(args);

//            // Add services to the container.
//            builder.Services.AddControllers().AddJsonOptions(options =>
//            {
//                options.JsonSerializerOptions.ReferenceHandler = System.Text.Json.Serialization.ReferenceHandler.IgnoreCycles;
//            });

//            builder.Services.AddDbContext<eMartDbContext>(options =>
//                options.UseMySql(builder.Configuration.GetConnectionString("emartConnection"),
//                ServerVersion.Parse("8.0.39-mysql")));

//            // Add services for repositories and services
//            builder.Services.AddScoped<ICategoryService, CategoryService>();
//            builder.Services.AddScoped<IProductService, ProductService>();
//            builder.Services.AddScoped<ISubCategoryService, SubCategoryService>();
//            builder.Services.AddScoped<IOrderService, OrderService>();
//            builder.Services.AddScoped<ICartService, CartService>();

//            // Enable CORS
//            builder.Services.AddCors(options =>
//            {
//                options.AddPolicy("AllowSpecificOrigin",
//                    policy => policy.WithOrigins("http://localhost:5173") // Replace with the appropriate frontend URL
//                                    .AllowAnyHeader()
//                                    .AllowAnyMethod());
//            });

//            builder.Services.AddEndpointsApiExplorer();
//            builder.Services.AddSwaggerGen();

//            var app = builder.Build();

//            // Configure the HTTP request pipeline.
//            if (app.Environment.IsDevelopment())
//            {
//                app.UseSwagger();
//                app.UseSwaggerUI();
//            }

//            app.UseHttpsRedirection();

//            // Apply CORS middleware before UseAuthorization and MapControllers
//            app.UseCors("AllowSpecificOrigin");

//            app.UseAuthorization();

//            app.MapControllers();

//            app.Run();
//        }
//    }
//}



//using E_mart.Repositories;
//using Microsoft.AspNetCore.Authentication.JwtBearer;
//using Microsoft.IdentityModel.Tokens;
//using Microsoft.OpenApi.Models;
//using System.Text;
//using E_mart.Services;
//using Microsoft.AspNetCore.Builder;
//using Microsoft.AspNetCore.Hosting;
//using Microsoft.AspNetCore.Http;
//using Microsoft.AspNetCore.Mvc;
//using Microsoft.EntityFrameworkCore;
//using Microsoft.Extensions.Configuration;
//using Microsoft.Extensions.DependencyInjection;
//using Microsoft.Extensions.Hosting;
//using E_mart.CustomMiddlewares;
//using Microsoft.AspNetCore.Diagnostics;

//public class Program
//{
//    public static void Main(string[] args)
//    {
//        var builder = WebApplication.CreateBuilder(args);

//        // Add services to the container.
//        builder.Services.AddControllers();

//        // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
//        builder.Services.AddEndpointsApiExplorer();
//        builder.Services.AddSwaggerGen();


//        builder.Services.AddDbContext<eMartDbContext>(options =>
//                        options.UseMySql(builder.Configuration.GetConnectionString("emartConnection"),
//                        ServerVersion.Parse("8.0.39-mysql")));

//        // Add services for repositories and services
//        builder.Services.AddScoped<IUserService, UserService>();
//        builder.Services.AddScoped<ICategoryService, CategoryService>();
//        builder.Services.AddScoped<IProductService, ProductService>();
//        builder.Services.AddScoped<ISubCategoryService, SubCategoryService>();
//        builder.Services.AddScoped<IOrderService, OrderService>();
//        builder.Services.AddScoped<ICartService, CartService>();

//        // Enable CORS
//        builder.Services.AddCors(options =>
//        {
//            options.AddPolicy("AllowSpecificOrigin",
//                policy => policy.WithOrigins("http://localhost:5173") // Replace with the appropriate frontend URL
//                                .AllowAnyHeader()
//                                .AllowAnyMethod());
//        });

//        //authentication code

//        builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
//            .AddJwtBearer(option =>
//            {
//                option.RequireHttpsMetadata = false;
//                option.SaveToken = true;
//                option.TokenValidationParameters = new TokenValidationParameters()
//                {
//                    ValidateIssuer = true,
//                    ValidateAudience = true,
//                    ValidAudience = builder.Configuration["Jwt:Audience"],
//                    ValidIssuer = builder.Configuration["Jwt:Issuer"],
//                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]))
//                };
//            }
//      );
//        builder.Services.AddSwaggerGen(c =>
//        {
//        c.SwaggerDoc("v1", new OpenApiInfo { Title = "Test01", Version = "v1" });
//        c.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme()
//        {
//            Name = "Authorization",
//            Type = SecuritySchemeType.ApiKey,
//            Scheme = "Bearer",
//            BearerFormat = "JWT",
//            In = ParameterLocation.Header,
//            Description = "JWT Authorization header using the Bearer scheme."

//        });
//            c.AddSecurityRequirement(new OpenApiSecurityRequirement
//            {
//                {        new OpenApiSecurityScheme
//                        {
//                            Reference = new OpenApiReference
//                            {
//                                Type = ReferenceType.SecurityScheme,
//                                Id = "Bearer"
//                            }
//                        },
//                        new string[] {}
//                }
//            });
//        });


//        //middleware customexception 

//        builder.Services.AddSingleton<GlobalExceptionMiddleware>();

//        var app = builder.Build();

//        // Configure the HTTP request pipeline.
//        if (app.Environment.IsDevelopment())
//        {
//            app.UseSwagger();
//            app.UseSwaggerUI();
//        }

//        //Middleware


//        app.UseMiddleware<GlobalExceptionMiddleware>();

//        app.UseCors("AllowSpecificOrigin");

//        app.UseHttpsRedirection();
//        app.UseAuthentication();
//        app.UseAuthorization();


//        app.MapControllers();

//        app.Run();
//    }
//}



using E_mart.Repositories;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Models;
using System.Text;
using E_mart.Services;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using E_mart.CustomMiddlewares;
using Microsoft.EntityFrameworkCore;

public class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);

        builder.Services.AddControllers();

        builder.Services.AddEndpointsApiExplorer();
        builder.Services.AddSwaggerGen();

        builder.Services.AddDbContext<eMartDbContext>(options =>
            options.UseMySql(builder.Configuration.GetConnectionString("emartConnection"),
            ServerVersion.Parse("8.0.39-mysql")));

        builder.Services.AddScoped<IUserService, UserService>();
        builder.Services.AddScoped<ICategoryService, CategoryService>();
        builder.Services.AddScoped<IProductService, ProductService>();
        builder.Services.AddScoped<ISubCategoryService, SubCategoryService>();
        builder.Services.AddScoped<IOrderService, OrderService>();
        builder.Services.AddScoped<ICartService, CartService>();

        builder.Services.AddCors(options =>
        {
            options.AddPolicy("AllowSpecificOrigin",
                policy => policy.WithOrigins("http://localhost:5174")
                                .AllowAnyHeader()
                                .AllowAnyMethod()
                                .AllowCredentials());
        });

        builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
            .AddJwtBearer(option =>
            {
                option.RequireHttpsMetadata = false;
                option.SaveToken = true;
                option.TokenValidationParameters = new TokenValidationParameters()
                {
                    ValidateIssuer = true,
                    ValidateAudience = true,
                    ValidAudience = builder.Configuration["Jwt:Audience"],
                    ValidIssuer = builder.Configuration["Jwt:Issuer"],
                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]))
                };
            });

        builder.Services.AddSwaggerGen(c =>
        {
            c.SwaggerDoc("v1", new OpenApiInfo { Title = "E-mart API", Version = "v1" });
            c.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme()
            {
                Name = "Authorization",
                Type = SecuritySchemeType.ApiKey,
                Scheme = "Bearer",
                BearerFormat = "JWT",
                In = ParameterLocation.Header,
                Description = "JWT Authorization header using the Bearer scheme."
            });
            c.AddSecurityRequirement(new OpenApiSecurityRequirement
            {
                { new OpenApiSecurityScheme { Reference = new OpenApiReference { Type = ReferenceType.SecurityScheme, Id = "Bearer" } }, new string[] {} }
            });
        });

        builder.Services.AddSingleton<GlobalExceptionMiddleware>();

        var app = builder.Build();

        if (app.Environment.IsDevelopment())
        {
            app.UseSwagger();
            app.UseSwaggerUI();
        }

        app.UseMiddleware<GlobalExceptionMiddleware>();
        app.UseCors("AllowSpecificOrigin");
        app.UseHttpsRedirection();
        app.UseAuthentication();
        app.UseAuthorization();

        app.MapControllers();

        app.Run();
    }
}




