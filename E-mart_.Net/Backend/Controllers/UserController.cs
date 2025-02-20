using E_mart.Models;
using E_mart.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity.Data;
using Microsoft.AspNetCore.Mvc;

namespace E_mart.Controllers
{
    [ApiController]
    [Route("api/user")]

    public class UserController : ControllerBase
    {
        private readonly IUserService _userService;
        private readonly ILogger<UserController> _logger;
        public UserController(IUserService userService, ILogger<UserController> logger)
        {
            _userService = userService;
            _logger = logger;
        }
        [HttpPost("Signup")]
        public async Task<IActionResult> CreateUser([FromBody] User user)
        {
            _logger.LogInformation("Received signup request for email: {Email}", user.Email);
            var existingUser = await _userService.FindUserByEmailAsync(user.Email);
            if (existingUser != null)
            {
                _logger.LogWarning("Signup attempt with already registered email: {Email}", user.Email);
                return BadRequest(new { message = "Email is already registered" });
            }
            var createdUser = await _userService.CreateUserAsync(user);
            _logger.LogInformation("Signup successful for email: {Email}", user.Email);
            return Created("Signup success", new { message = "Signup successful", user = createdUser });

        }
        [HttpPost("Signin")]
        public async Task<IActionResult> LoginUser([FromBody] LoginRequest loginRequest)
        {
            _logger.LogInformation("Received login request for email: {Email}", loginRequest.Email);

            var user = await _userService.ValidateUserCredentialsAsync(loginRequest.Email, loginRequest.Password);
            if (user == null)
            {
                _logger.LogError("Invalid login attempt for email: {Email}", loginRequest.Email);
                return Unauthorized(new { message = "Invalid email or password" });
            }

            _logger.LogInformation("Login successful for email: {Email}", loginRequest.Email);
            return Ok(new { message = "Signin success", user });
        }
    }
}
